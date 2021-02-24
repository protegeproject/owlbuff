package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.AnnotationSubject;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnnotationSubjectVisitorEx;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlAnnotationSubject2Msg {

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Nonnull
    private final OwlAnonymousIndividual2Msg anonymousIndividual2Msg;

    private OWLAnnotationSubjectVisitorEx<AnnotationSubject> visitor = new OWLAnnotationSubjectVisitorEx<>() {
        @Nonnull
        @Override
        public AnnotationSubject visit(@Nonnull IRI iri) {
            return AnnotationSubject.newBuilder().setIri(iri2Msg.toMsg(iri)).build();
        }

        @Nonnull
        @Override
        public AnnotationSubject visit(@Nonnull OWLAnonymousIndividual owlAnonymousIndividual) {
            return AnnotationSubject.newBuilder()
                                    .setAnonymousIndividual(anonymousIndividual2Msg.toMsg(owlAnonymousIndividual))
                                    .build();
        }
    };;

    @Inject
    public OwlAnnotationSubject2Msg(@Nonnull Iri2Msg iri2Msg,
                                    @Nonnull OwlAnonymousIndividual2Msg anonymousIndividual2Msg) {
        this.iri2Msg = checkNotNull(iri2Msg);
        this.anonymousIndividual2Msg = checkNotNull(anonymousIndividual2Msg);
    }

    @Nonnull
    public AnnotationSubject toMsg(@Nonnull OWLAnnotationSubject subject) {
        return subject.accept(visitor);
    }

}
