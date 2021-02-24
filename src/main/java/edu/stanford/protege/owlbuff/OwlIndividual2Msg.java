package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.Individual;
import edu.stanford.protege.owlbuf.NamedIndividual;
import org.semanticweb.owlapi.model.OWLIndividual;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlIndividual2Msg {

    @Nonnull
    private final OwlNamedIndividual2Msg namedIndividual2Msg;

    @Nonnull
    private final OwlAnonymousIndividual2Msg anonymousIndividual2Msg;

    @Inject
    public OwlIndividual2Msg(@Nonnull OwlNamedIndividual2Msg namedIndividual2Msg,
                             @Nonnull OwlAnonymousIndividual2Msg anonymousIndividual2Msg) {
        this.namedIndividual2Msg = checkNotNull(namedIndividual2Msg);
        this.anonymousIndividual2Msg = checkNotNull(anonymousIndividual2Msg);
    }

    @Nonnull
    public Individual toMsg(@Nonnull OWLIndividual individual) {
        if (individual.isAnonymous()) {
            var anonymousIndividual = anonymousIndividual2Msg.toMsg(individual.asOWLAnonymousIndividual());
            return Individual.newBuilder().setAnonymousIndividual(anonymousIndividual).build();
        }
        else {
            var namedIndividual = namedIndividual2Msg.toMsg(individual.asOWLNamedIndividual());
            return Individual.newBuilder().setNamedIndividual(namedIndividual).build();
        }
    }
}
