package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.SubClassOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class OwlSubClassOfAxiom2Msg {

    @Nonnull
    private final OwlClassExpression2Msg classExpression2Msg;

    @Nonnull
    private final OwlAxiomAnnotations2Msg axiomAnnotations2Msg;

    @Inject
    public OwlSubClassOfAxiom2Msg(@Nonnull OwlClassExpression2Msg classExpression2Msg,
                                  @Nonnull OwlAxiomAnnotations2Msg axiomAnnotations2Msg) {
        this.classExpression2Msg = checkNotNull(classExpression2Msg);
        this.axiomAnnotations2Msg = checkNotNull(axiomAnnotations2Msg);
    }

    @Nonnull
    public SubClassOf toMsg(@Nonnull OWLSubClassOfAxiom owlSubClassOfAxiom) {
        var subClassExpr = classExpression2Msg.toMsg(owlSubClassOfAxiom.getSubClass());
        var superClassExpr = classExpression2Msg.toMsg(owlSubClassOfAxiom.getSuperClass());
        var annotations = axiomAnnotations2Msg.toMsg(owlSubClassOfAxiom);
        return SubClassOf.newBuilder()
                         .setSubCls(subClassExpr)
                         .setSuperCls(superClassExpr)
                         .addAllAnnotations(annotations)
                         .build();
    }
}