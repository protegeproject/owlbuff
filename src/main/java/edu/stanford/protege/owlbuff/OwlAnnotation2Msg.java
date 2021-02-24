package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.Annotation;
import org.semanticweb.owlapi.model.OWLAnnotation;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlAnnotation2Msg {

    @Nonnull
    private final OwlAnnotationProperty2Msg annotationProperty2Msg;

    @Nonnull
    private final OwlAnnotationValue2Msg annotationValue2Msg;

    @Inject
    public OwlAnnotation2Msg(@Nonnull OwlAnnotationProperty2Msg annotationProperty2Msg,
                             @Nonnull OwlAnnotationValue2Msg annotationValue2Msg) {
        this.annotationProperty2Msg = checkNotNull(annotationProperty2Msg);
        this.annotationValue2Msg = checkNotNull(annotationValue2Msg);
    }

    @Nonnull
    public Annotation toMsg(@Nonnull OWLAnnotation annotation) {
        var property = annotationProperty2Msg.toMsg(annotation.getProperty());
        var value = annotationValue2Msg.toMsg(annotation.getValue());
        return Annotation.newBuilder()
                  .setAnnotationProperty(property)
                  .setAnnotationValue(value)
                  .build();
    }
}
