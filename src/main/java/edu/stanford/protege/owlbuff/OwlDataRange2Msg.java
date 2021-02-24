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
public class OwlDataRange2Msg {

    @Nonnull
    private final OwlDatatype2Msg datatype2Msg;

    @Nonnull
    private final OwlLiteral2Msg literal2Msg;

    @Nonnull
    private final Iri2Msg iri2Msg;

    private final OWLDataRangeVisitorEx<DataRange> rangeVisitorEx = new OWLDataRangeVisitorEx<>() {
        @Nonnull
        @Override
        public DataRange visit(@Nonnull OWLDatatype owlDatatype) {
            return DataRange.newBuilder()
                    .setDatatype(datatype2Msg.toMsg(owlDatatype))
                    .build();
        }

        @Nonnull
        @Override
        public DataRange visit(@Nonnull OWLDataOneOf owlDataOneOf) {
            var builder = DataOneOf.newBuilder();
            owlDataOneOf.getValues()
                        .stream()
                        .map(literal2Msg::toMsg)
                        .forEach(builder::addLiterals);
            var dataOneOf = builder.build();
            return DataRange.newBuilder()
                    .setDataOneOf(dataOneOf)
                    .build();
        }

        @Nonnull
        @Override
        public DataRange visit(@Nonnull OWLDataComplementOf owlDataComplementOf) {
            var complementedDataRange = toMsg(owlDataComplementOf.getDataRange());
            var dataComplementOf = DataComplementOf.newBuilder()
                    .setComplementedDataRange(complementedDataRange)
                    .build();
            return DataRange.newBuilder()
                    .setDataComplementOf(dataComplementOf)
                    .build();
        }

        @Nonnull
        @Override
        public DataRange visit(@Nonnull OWLDataIntersectionOf owlDataIntersectionOf) {
            var builder = DataIntersectionOf.newBuilder();
            owlDataIntersectionOf.getOperands()
                        .stream()
                        .map(dataRange -> toMsg(dataRange))
                        .forEach(builder::addDataRanges);
            var dataIntersectionOf = builder.build();
            return DataRange.newBuilder()
                            .setDataIntersectionOf(dataIntersectionOf)
                            .build();
        }

        @Nonnull
        @Override
        public DataRange visit(@Nonnull OWLDataUnionOf owlDataUnionOf) {
            var builder = DataUnionOf.newBuilder();
            owlDataUnionOf.getOperands()
                                 .stream()
                                 .map(dataRange -> toMsg(dataRange))
                                 .forEach(builder::addDataRanges);
            var dataUnionOf = builder.build();
            return DataRange.newBuilder()
                            .setDataUnionOf(dataUnionOf)
                            .build();
        }

        @Nonnull
        @Override
        public DataRange visit(@Nonnull OWLDatatypeRestriction owlDatatypeRestriction) {
            var builder = DatatypeRestriction.newBuilder();
            builder.setDatatype(datatype2Msg.toMsg(owlDatatypeRestriction.getDatatype()));
            owlDatatypeRestriction.getFacetRestrictions()
                                  .stream()
                                  .map(fr ->
                                      DatatypeFacetRestriction.newBuilder()
                                                              .setFacetIri(iri2Msg.toMsg(fr.getFacet().getIRI()))
                                                              .setFacetValue(literal2Msg.toMsg(fr.getFacetValue()))
                                  )
                                  .forEach(builder::addFacetRestrictions);
            var datatypeRestriction = builder.build();
            return DataRange.newBuilder()
                    .setDatatypeRestriction(datatypeRestriction)
                    .build();
        }
    };

    @Inject
    public OwlDataRange2Msg(@Nonnull OwlDatatype2Msg datatype2Msg,
                            @Nonnull OwlLiteral2Msg literal2Msg,
                            @Nonnull Iri2Msg iri2Msg) {
        this.datatype2Msg = checkNotNull(datatype2Msg);
        this.literal2Msg = checkNotNull(literal2Msg);
        this.iri2Msg = checkNotNull(iri2Msg);
    }

    @Nonnull
    DataRange toMsg(@Nonnull OWLDataRange dataRange) {
        return dataRange.accept(rangeVisitorEx);
    }
}
