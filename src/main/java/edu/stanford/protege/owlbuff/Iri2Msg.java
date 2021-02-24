package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.Iri;
import edu.stanford.protege.owlbuf.IriOrBuilder;
import org.semanticweb.owlapi.model.IRI;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class Iri2Msg {

    @Inject
    public Iri2Msg() {
    }

    @Nonnull
    public Iri toMsg(@Nonnull IRI iri) {
        return Iri.newBuilder()
                .setLexicalValue(iri.toString())
                .build();
    }
}
