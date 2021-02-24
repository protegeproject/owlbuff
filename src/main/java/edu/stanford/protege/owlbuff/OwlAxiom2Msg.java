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
public class OwlAxiom2Msg {

    @Nonnull
    private final OwlClassExpression2Msg classExpression2Msg;

    @Nonnull
    private final OwlAnnotation2Msg annotation2Msg;

    @Nonnull
    private final OwlAxiomAnnotations2Msg axiomAnnotations2Msg;

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Nonnull
    private final OwlEntity2Msg entity2Msg;

    @Nonnull
    private final OwlAnnotationProperty2Msg annotationProperty2Msg;

    @Nonnull
    private final OwlAnnotationValue2Msg annotationValue2Msg;

    @Nonnull
    private final OwlAnnotationSubject2Msg annotationSubject2Msg;

    @Nonnull
    private final OwlSubClassOfAxiom2Msg subClassOfAxiom2Msg;

    @Nonnull
    private final OwlDeclarationAxiom2Msg declarationAxiom2Msg;

    @Nonnull
    private final OwlAnnotationAssertionAxiom2Msg annotationAssertionAxiom2Msg;

    @Nonnull
    private final OwlClassAssertionAxiom2Msg classAssertionAxiom2Msg;

    private final OWLAxiomVisitorEx<Axiom> visitor = new OWLAxiomVisitorEx<Axiom>() {
        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDeclarationAxiom owlDeclarationAxiom) {
            var declaration = declarationAxiom2Msg.toMsg(owlDeclarationAxiom);
            return Axiom.newBuilder().setDeclaration(declaration).build();
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDatatypeDefinitionAxiom owlDatatypeDefinitionAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLAnnotationAssertionAxiom owlAnnotationAssertionAxiom) {
            var annotationAssertion = annotationAssertionAxiom2Msg.toMsg(owlAnnotationAssertionAxiom);
            return Axiom.newBuilder().setAnnotationAssertion(annotationAssertion).build();
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLSubAnnotationPropertyOfAxiom owlSubAnnotationPropertyOfAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLAnnotationPropertyDomainAxiom owlAnnotationPropertyDomainAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLAnnotationPropertyRangeAxiom owlAnnotationPropertyRangeAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLSubClassOfAxiom owlSubClassOfAxiom) {
            var subClassOf = owlSubClassOfAxiom2Msg.toMsg(owlSubClassOfAxiom);
            return Axiom.newBuilder().setSubClassOf(subClassOf).build();
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLNegativeObjectPropertyAssertionAxiom owlNegativeObjectPropertyAssertionAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLAsymmetricObjectPropertyAxiom owlAsymmetricObjectPropertyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLReflexiveObjectPropertyAxiom owlReflexiveObjectPropertyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDisjointClassesAxiom owlDisjointClassesAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDataPropertyDomainAxiom owlDataPropertyDomainAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLObjectPropertyDomainAxiom owlObjectPropertyDomainAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLEquivalentObjectPropertiesAxiom owlEquivalentObjectPropertiesAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLNegativeDataPropertyAssertionAxiom owlNegativeDataPropertyAssertionAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDifferentIndividualsAxiom owlDifferentIndividualsAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDisjointDataPropertiesAxiom owlDisjointDataPropertiesAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDisjointObjectPropertiesAxiom owlDisjointObjectPropertiesAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLObjectPropertyRangeAxiom owlObjectPropertyRangeAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLObjectPropertyAssertionAxiom owlObjectPropertyAssertionAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLFunctionalObjectPropertyAxiom owlFunctionalObjectPropertyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLSubObjectPropertyOfAxiom owlSubObjectPropertyOfAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDisjointUnionAxiom owlDisjointUnionAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLSymmetricObjectPropertyAxiom owlSymmetricObjectPropertyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDataPropertyRangeAxiom owlDataPropertyRangeAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLFunctionalDataPropertyAxiom owlFunctionalDataPropertyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLEquivalentDataPropertiesAxiom owlEquivalentDataPropertiesAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLClassAssertionAxiom owlClassAssertionAxiom) {
            var classAssertion = classAssertionAxiom2Msg.toMsg(owlClassAssertionAxiom);
            return Axiom.newBuilder().setClassAssertion(classAssertion).build();
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLEquivalentClassesAxiom owlEquivalentClassesAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLDataPropertyAssertionAxiom owlDataPropertyAssertionAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLTransitiveObjectPropertyAxiom owlTransitiveObjectPropertyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLIrreflexiveObjectPropertyAxiom owlIrreflexiveObjectPropertyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLSubDataPropertyOfAxiom owlSubDataPropertyOfAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLInverseFunctionalObjectPropertyAxiom owlInverseFunctionalObjectPropertyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLSameIndividualAxiom owlSameIndividualAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLSubPropertyChainOfAxiom owlSubPropertyChainOfAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLInverseObjectPropertiesAxiom owlInverseObjectPropertiesAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull OWLHasKeyAxiom owlHasKeyAxiom) {
            return null;
        }

        @Nonnull
        @Override
        public Axiom visit(@Nonnull SWRLRule swrlRule) {
            return null;
        }
    };

    @Nonnull
    private final OwlSubClassOfAxiom2Msg owlSubClassOfAxiom2Msg;


    @Nonnull
    private final OwlIndividual2Msg individual2Msg;


    @Inject
    public OwlAxiom2Msg(@Nonnull OwlClassExpression2Msg classExpression2Msg,
                        @Nonnull OwlAnnotation2Msg annotation2Msg,
                        @Nonnull OwlAxiomAnnotations2Msg axiomAnnotations2Msg,
                        @Nonnull Iri2Msg iri2Msg,
                        @Nonnull OwlEntity2Msg entity2Msg,
                        @Nonnull OwlAnnotationProperty2Msg annotationProperty2Msg,
                        @Nonnull OwlAnnotationValue2Msg annotationValue2Msg,
                        @Nonnull OwlAnnotationSubject2Msg annotationSubject2Msg,
                        @Nonnull OwlSubClassOfAxiom2Msg subClassOfAxiom2Msg,
                        @Nonnull OwlDeclarationAxiom2Msg declarationAxiom2Msg,
                        @Nonnull OwlAnnotationAssertionAxiom2Msg annotationAssertionAxiom2Msg,
                        @Nonnull OwlClassAssertionAxiom2Msg classAssertionAxiom2Msg,
                        @Nonnull OwlSubClassOfAxiom2Msg owlSubClassOfAxiom2Msg,
                        @Nonnull OwlIndividual2Msg individual2Msg) {
        this.classExpression2Msg = checkNotNull(classExpression2Msg);
        this.annotation2Msg = checkNotNull(annotation2Msg);
        this.axiomAnnotations2Msg = axiomAnnotations2Msg;
        this.iri2Msg = checkNotNull(iri2Msg);
        this.entity2Msg = checkNotNull(entity2Msg);
        this.annotationProperty2Msg = annotationProperty2Msg;
        this.annotationValue2Msg = annotationValue2Msg;
        this.annotationSubject2Msg = annotationSubject2Msg;
        this.subClassOfAxiom2Msg = subClassOfAxiom2Msg;
        this.declarationAxiom2Msg = declarationAxiom2Msg;
        this.annotationAssertionAxiom2Msg = annotationAssertionAxiom2Msg;
        this.classAssertionAxiom2Msg = classAssertionAxiom2Msg;
        this.owlSubClassOfAxiom2Msg = owlSubClassOfAxiom2Msg;
        this.individual2Msg = checkNotNull(individual2Msg);
    }

    @Nonnull
    public Axiom toMsg(@Nonnull OWLAxiom axiom) {
        return axiom.accept(visitor);
    }

}
