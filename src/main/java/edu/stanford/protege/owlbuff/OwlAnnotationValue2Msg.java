package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.AnnotationValue;
import org.semanticweb.owlapi.model.*;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlAnnotationValue2Msg {

    @Nonnull
    private final OwlLiteral2Msg literal2Msg;

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Nonnull
    private final OwlAnonymousIndividual2Msg anonymousIndividual2Msg;

    private final OWLAnnotationValueVisitorEx<AnnotationValue> visitor = new OWLAnnotationValueVisitorEx<>() {
        @Nonnull
        @Override
        public AnnotationValue visit(@Nonnull IRI iri) {
            var i = iri2Msg.toMsg(iri);
            return AnnotationValue.newBuilder().setIri(i).build();
        }

        @Nonnull
        @Override
        public AnnotationValue visit(@Nonnull OWLAnonymousIndividual owlAnonymousIndividual) {
            var i = anonymousIndividual2Msg.toMsg(owlAnonymousIndividual);
            return AnnotationValue.newBuilder().setAnonymousIndividual(i).build();
        }

        @Nonnull
        @Override
        public AnnotationValue visit(@Nonnull OWLLiteral owlLiteral) {
            var l = literal2Msg.toMsg(owlLiteral);
            return AnnotationValue.newBuilder().setLiteral(l).build();
        }
    };

    @Inject
    public OwlAnnotationValue2Msg(@Nonnull OwlLiteral2Msg literal2Msg,
                                  @Nonnull Iri2Msg iri2Msg,
                                  @Nonnull OwlAnonymousIndividual2Msg anonymousIndividual2Msg) {
        this.literal2Msg = checkNotNull(literal2Msg);
        this.iri2Msg = checkNotNull(iri2Msg);
        this.anonymousIndividual2Msg = checkNotNull(anonymousIndividual2Msg);
    }

    @Nonnull
    public AnnotationValue toMsg(@Nonnull OWLAnnotationValue value) {
        return value.accept(visitor);
    }
}
