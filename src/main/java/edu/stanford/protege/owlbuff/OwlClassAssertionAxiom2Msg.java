package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.ClassAssertion;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlClassAssertionAxiom2Msg {

    @Nonnull
    private final OwlClassExpression2Msg classExpression2Msg;

    @Nonnull
    private final OwlIndividual2Msg individual2Msg;

    @Nonnull
    private final OwlAxiomAnnotations2Msg axiomAnnotations2Msg;

    @Inject
    public OwlClassAssertionAxiom2Msg(@Nonnull OwlClassExpression2Msg classExpression2Msg,
                                      @Nonnull OwlIndividual2Msg individual2Msg,
                                      @Nonnull OwlAxiomAnnotations2Msg axiomAnnotations2Msg) {
        this.classExpression2Msg = classExpression2Msg;
        this.individual2Msg = individual2Msg;
        this.axiomAnnotations2Msg = axiomAnnotations2Msg;
    }

    public ClassAssertion toMsg(@Nonnull OWLClassAssertionAxiom axiom) {
        var classExpr = classExpression2Msg.toMsg(axiom.getClassExpression());
        var individual = individual2Msg.toMsg(axiom.getIndividual());
        var annotations = axiomAnnotations2Msg.toMsg(axiom);
        return ClassAssertion.newBuilder()
                             .setClassExpr(classExpr)
                             .setIndividual(individual)
                             .addAllAnnotations(annotations)
                             .build();
    }
}
