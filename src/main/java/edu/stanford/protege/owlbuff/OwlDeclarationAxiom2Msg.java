package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.Declaration;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlDeclarationAxiom2Msg {

    @Nonnull
    private final OwlEntity2Msg entity2Msg;

    @Nonnull
    private final OwlAxiomAnnotations2Msg axiomAnnotations2Msg;

    @Inject
    public OwlDeclarationAxiom2Msg(@Nonnull OwlEntity2Msg entity2Msg,
                                   @Nonnull OwlAxiomAnnotations2Msg axiomAnnotations2Msg) {
        this.entity2Msg = checkNotNull(entity2Msg);
        this.axiomAnnotations2Msg = checkNotNull(axiomAnnotations2Msg);
    }

    @Nonnull
    public Declaration toMsg(@Nonnull OWLDeclarationAxiom axiom) {
        var entity = entity2Msg.toMsg(axiom.getEntity());
        var annotations = axiomAnnotations2Msg.toMsg(axiom);
        return Declaration.newBuilder().setEntity(entity).addAllAnnotations(annotations).build();
    }
}
