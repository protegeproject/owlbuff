package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.*;
import org.semanticweb.owlapi.model.*;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlClassExpression2Msg {

    @Nonnull
    private final OwlClass2Msg owlClass2Msg;

    @Nonnull
    private final OwlIndividual2Msg individual2Msg;

    @Nonnull
    private final OwlObjectPropertyExpression2Msg objectPropertyExpression2Msg;

    @Nonnull
    private final OwlDataProperty2Msg dataProperty2Msg;

    @Nonnull
    private final OwlDataRange2Msg dataRange2Msg;

    @Nonnull
    private final OwlLiteral2Msg literal2Msg;

    @Nonnull
    private final OWLClassExpressionVisitorEx<ClassExpression> visitor = new OWLClassExpressionVisitorEx<>() {
        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLClass owlClass) {
            return ClassExpression.newBuilder().setOwlClass(owlClass2Msg.toMsg(owlClass)).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectIntersectionOf owlObjectIntersectionOf) {
            var builder = ObjectIntersectionOf.newBuilder();
            owlObjectIntersectionOf.getOperands()
                                   .stream()
                                   .map(ce -> toMsg(ce))
                                   .forEach(builder::addClassExpressions);
            var objectIntersectionOf = builder.build();
            return ClassExpression.newBuilder().setObjectIntersectionOf(objectIntersectionOf).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectUnionOf owlObjectUnionOf) {
            var builder = ObjectUnionOf.newBuilder();
            owlObjectUnionOf.getOperands()
                                   .stream()
                                   .map(ce -> toMsg(ce))
                                   .forEach(builder::addClassExpressions);
            var objectUnionOf = builder.build();
            return ClassExpression.newBuilder().setObjectUnionOf(objectUnionOf).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectComplementOf owlObjectComplementOf) {
            var complementedClassExpression = toMsg(owlObjectComplementOf.getOperand());
            var objectComplementOf = ObjectComplementOf.newBuilder()
                    .setClassExpr(complementedClassExpression)
                    .build();
            return ClassExpression.newBuilder().setObjectComplementOf(objectComplementOf).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectSomeValuesFrom owlObjectSomeValuesFrom) {
            var property = objectPropertyExpression2Msg.toMsg(owlObjectSomeValuesFrom.getProperty());
            var filler = toMsg(owlObjectSomeValuesFrom.getFiller());
            var objectSomeValuesFrom = ObjectSomeValuesFrom.newBuilder()
                                                           .setProperty(property)
                                                           .setFiller(filler)
                                                           .build();
            return ClassExpression.newBuilder().setObjectSomeValuesFrom(objectSomeValuesFrom).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectAllValuesFrom owlObjectAllValuesFrom) {
            var property = objectPropertyExpression2Msg.toMsg(owlObjectAllValuesFrom.getProperty());
            var filler = toMsg(owlObjectAllValuesFrom.getFiller());
            var objectAllValuesFrom = ObjectAllValuesFrom.newBuilder().setProperty(property).setFiller(filler).build();
            return ClassExpression.newBuilder().setObjectAllValuesFrom(objectAllValuesFrom).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectHasValue owlObjectHasValue) {
            var property = objectPropertyExpression2Msg.toMsg(owlObjectHasValue.getProperty());
            var filler = individual2Msg.toMsg(owlObjectHasValue.getFiller());
            var objectHasValue = ObjectHasValue.newBuilder().setProperty(property).setFiller(filler).build();
            return ClassExpression.newBuilder().setObjectHasValue(objectHasValue).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectMinCardinality owlObjectMinCardinality) {
            var property = objectPropertyExpression2Msg.toMsg(owlObjectMinCardinality.getProperty());
            var filler = toMsg(owlObjectMinCardinality.getFiller());
            var cardinality = owlObjectMinCardinality.getCardinality();
            var objectMinCardinality = ObjectMinCardinality.newBuilder()
                                                           .setProperty(property)
                                                           .setFiller(filler)
                                                           .setCardinality(cardinality)
                                                           .build();
            return ClassExpression.newBuilder().setObjectMinCardinality(objectMinCardinality).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectExactCardinality owlObjectExactCardinality) {
            var property = objectPropertyExpression2Msg.toMsg(owlObjectExactCardinality.getProperty());
            var filler = toMsg(owlObjectExactCardinality.getFiller());
            var cardinality = owlObjectExactCardinality.getCardinality();
            var objectExactCardinality = ObjectExactCardinality.newBuilder()
                                                           .setProperty(property)
                                                           .setFiller(filler)
                                                           .setCardinality(cardinality)
                                                           .build();
            return ClassExpression.newBuilder().setObjectExactCardinality(objectExactCardinality).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectMaxCardinality owlObjectMaxCardinality) {
            var property = objectPropertyExpression2Msg.toMsg(owlObjectMaxCardinality.getProperty());
            var filler = toMsg(owlObjectMaxCardinality.getFiller());
            var cardinality = owlObjectMaxCardinality.getCardinality();
            var objectMaxCardinality = ObjectMaxCardinality.newBuilder()
                                                           .setProperty(property)
                                                           .setFiller(filler)
                                                           .setCardinality(cardinality)
                                                           .build();
            return ClassExpression.newBuilder().setObjectMaxCardinality(objectMaxCardinality).build();

        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectHasSelf owlObjectHasSelf) {
            var property = objectPropertyExpression2Msg.toMsg(owlObjectHasSelf.getProperty());
            var hasSelf = ObjectHasSelf.newBuilder()
                    .setProperty(property);
            return ClassExpression.newBuilder().setObjectHasSelf(hasSelf).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLObjectOneOf owlObjectOneOf) {
            var builder = ObjectOneOf.newBuilder();
            owlObjectOneOf.getIndividuals()
                          .stream()
                          .map(individual2Msg::toMsg)
                          .forEach(builder::addIndividual);
            var objectOneOf = builder.build();
            return ClassExpression.newBuilder()
                    .setObjectOneOf(objectOneOf)
                    .build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLDataSomeValuesFrom owlDataSomeValuesFrom) {
            var property = dataProperty2Msg.toMsg(owlDataSomeValuesFrom.getProperty());
            var filler = dataRange2Msg.toMsg(owlDataSomeValuesFrom.getFiller());
            var dataSomeValuesFrom = DataSomeValuesFrom.newBuilder()
                                                           .setProperty(property)
                                                           .setFiller(filler)
                                                           .build();
            return ClassExpression.newBuilder().setDataSomeValuesFrom(dataSomeValuesFrom).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLDataAllValuesFrom owlDataAllValuesFrom) {
            var property = dataProperty2Msg.toMsg(owlDataAllValuesFrom.getProperty());
            var filler = dataRange2Msg.toMsg(owlDataAllValuesFrom.getFiller());
            var dataAllValuesFrom = DataAllValuesFrom.newBuilder().setProperty(property).setFiller(filler).build();
            return ClassExpression.newBuilder().setDataAllValuesFrom(dataAllValuesFrom).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLDataHasValue owlDataHasValue) {
            var property = dataProperty2Msg.toMsg(owlDataHasValue.getProperty());
            var filler = literal2Msg.toMsg(owlDataHasValue.getFiller());
            var dataHasValue = DataHasValue.newBuilder().setProperty(property).setFiller(filler).build();
            return ClassExpression.newBuilder().setDataHasValue(dataHasValue).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLDataMinCardinality owlDataMinCardinality) {
            var property = dataProperty2Msg.toMsg(owlDataMinCardinality.getProperty());
            var filler = dataRange2Msg.toMsg(owlDataMinCardinality.getFiller());
            var cardinality = owlDataMinCardinality.getCardinality();
            var dataMinCardinality = DataMinCardinality.newBuilder()
                                                           .setProperty(property)
                                                           .setFiller(filler)
                                                           .setCardinality(cardinality)
                                                           .build();
            return ClassExpression.newBuilder().setDataMinCardinality(dataMinCardinality).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLDataExactCardinality owlDataExactCardinality) {
            var property = dataProperty2Msg.toMsg(owlDataExactCardinality.getProperty());
            var filler = dataRange2Msg.toMsg(owlDataExactCardinality.getFiller());
            var cardinality = owlDataExactCardinality.getCardinality();
            var dataExactCardinality = DataExactCardinality.newBuilder()
                                                               .setProperty(property)
                                                               .setFiller(filler)
                                                               .setCardinality(cardinality)
                                                               .build();
            return ClassExpression.newBuilder().setDataExactCardinality(dataExactCardinality).build();
        }

        @Nonnull
        @Override
        public ClassExpression visit(@Nonnull OWLDataMaxCardinality owlDataMaxCardinality) {
            var property = dataProperty2Msg.toMsg(owlDataMaxCardinality.getProperty());
            var filler = dataRange2Msg.toMsg(owlDataMaxCardinality.getFiller());
            var cardinality = owlDataMaxCardinality.getCardinality();
            var dataMaxCardinality = DataMaxCardinality.newBuilder()
                                                           .setProperty(property)
                                                           .setFiller(filler)
                                                           .setCardinality(cardinality)
                                                           .build();
            return ClassExpression.newBuilder().setDataMaxCardinality(dataMaxCardinality).build();

        }
    };

    @Inject
    public OwlClassExpression2Msg(@Nonnull OwlClass2Msg owlClass2Msg,
                                  @Nonnull OwlIndividual2Msg individual2Msg,
                                  @Nonnull OwlObjectPropertyExpression2Msg objectPropertyExpression2Msg,
                                  @Nonnull OwlDataProperty2Msg dataProperty2Msg,
                                  @Nonnull OwlDataRange2Msg dataRange2Msg,
                                  @Nonnull OwlLiteral2Msg literal2Msg) {
        this.owlClass2Msg = checkNotNull(owlClass2Msg);
        this.individual2Msg = checkNotNull(individual2Msg);
        this.objectPropertyExpression2Msg = checkNotNull(objectPropertyExpression2Msg);
        this.dataProperty2Msg = checkNotNull(dataProperty2Msg);
        this.dataRange2Msg = checkNotNull(dataRange2Msg);
        this.literal2Msg = checkNotNull(literal2Msg);
    }

    @Nonnull
    public ClassExpression toMsg(@Nonnull OWLClassExpression classExpression) {
        return classExpression.accept(visitor);
    }
}
