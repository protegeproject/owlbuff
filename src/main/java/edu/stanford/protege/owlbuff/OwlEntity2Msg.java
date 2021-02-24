package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.*;
import edu.stanford.protege.owlbuf.Entity;
import org.semanticweb.owlapi.model.*;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlEntity2Msg {

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Inject
    public OwlEntity2Msg(@Nonnull Iri2Msg iri2Msg) {
        this.iri2Msg = checkNotNull(iri2Msg);
    }

    public Entity toMsg(@Nonnull OWLEntity entity) {
        return entity.accept(new OWLEntityVisitorEx<Entity>() {
            @Nonnull
            @Override
            public Entity visit(@Nonnull OWLClass owlClass) {
                var cls = Cls.newBuilder().setIri(toIri(owlClass.getIRI())).build();
                return Entity.newBuilder().setCls(cls).build();
            }

            @Nonnull
            @Override
            public Entity visit(@Nonnull OWLObjectProperty owlObjectProperty) {
                var cls = ObjectProperty.newBuilder().setIri(toIri(owlObjectProperty.getIRI())).build();
                return Entity.newBuilder().setObjectProperty(cls).build();
            }

            @Nonnull
            @Override
            public Entity visit(@Nonnull OWLDataProperty owlDataProperty) {
                var prop = DataProperty.newBuilder().setIri(iri2Msg.toMsg(owlDataProperty.getIRI()));
                return Entity.newBuilder().setDataProperty(prop).build();
            }

            @Nonnull
            @Override
            public Entity visit(@Nonnull OWLNamedIndividual owlNamedIndividual) {
                var individual = NamedIndividual.newBuilder().setIri(iri2Msg.toMsg(owlNamedIndividual.getIRI()));
                return Entity.newBuilder().setNamedIndividual(individual).build();
            }

            @Nonnull
            @Override
            public Entity visit(@Nonnull OWLDatatype owlDatatype) {
                var datatype = Datatype.newBuilder().setIri(iri2Msg.toMsg(owlDatatype.getIRI()));
                return Entity.newBuilder().setDatatype(datatype).build();
            }

            @Nonnull
            @Override
            public Entity visit(@Nonnull OWLAnnotationProperty owlAnnotationProperty) {
                var prop = AnnotationProperty.newBuilder().setIri(iri2Msg.toMsg(owlAnnotationProperty.getIRI()));
                return Entity.newBuilder().setAnnotationProperty(prop).build();

            }
        });
    }

    private Iri toIri(IRI iri) {
        return iri2Msg.toMsg(iri);
    }
}
