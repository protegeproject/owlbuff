package edu.stanford.protege.owlbuff;

import dagger.Component;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
@Component(modules = AxiomModule.class)
public interface AxiomMessageComponent {

    OwlAxiom2Msg getAxiom2Msg();
}
