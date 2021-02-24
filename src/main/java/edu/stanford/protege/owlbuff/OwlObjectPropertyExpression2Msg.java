package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.ObjectPropertyExpression;
import edu.stanford.protege.owlbuf.ObjectPropertyInverse;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlObjectPropertyExpression2Msg {

    @Nonnull
    private final OwlObjectProperty2Msg owlObjectProperty2Msg;

    @Inject
    public OwlObjectPropertyExpression2Msg(@Nonnull OwlObjectProperty2Msg owlObjectProperty2Msg) {
        this.owlObjectProperty2Msg = checkNotNull(owlObjectProperty2Msg);
    }

    @Nonnull
    public ObjectPropertyExpression toMsg(@Nonnull OWLObjectPropertyExpression propertyExpression) {
        if (propertyExpression.isAnonymous()) {
            var property = owlObjectProperty2Msg.toMsg(propertyExpression.getNamedProperty());
            var inverse = ObjectPropertyInverse.newBuilder()
                                 .setProperty(property);
            return ObjectPropertyExpression.newBuilder().setObjectPropertyInverse(inverse).build();
        }
        else {
            var property = owlObjectProperty2Msg.toMsg(propertyExpression.asOWLObjectProperty());
            return ObjectPropertyExpression.newBuilder().setObjectProperty(property).build();
        }
    }
}
