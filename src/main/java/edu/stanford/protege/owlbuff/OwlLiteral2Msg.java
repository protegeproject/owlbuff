package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.Literal;
import org.semanticweb.owlapi.model.OWLLiteral;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlLiteral2Msg {

    @Nonnull
    private final OwlDatatype2Msg datatype2Msg;

    @Inject
    public OwlLiteral2Msg(@Nonnull OwlDatatype2Msg datatype2Msg) {
        this.datatype2Msg = checkNotNull(datatype2Msg);
    }

    @Nonnull
    public Literal toMsg(@Nonnull OWLLiteral literal) {
        var datatype = datatype2Msg.toMsg(literal.getDatatype());
        return Literal.newBuilder()
                .setDatatype(datatype)
                .setLexicalValue(literal.getLiteral())
                .setLanguageTag(literal.getLang())
                .build();
    }
}
