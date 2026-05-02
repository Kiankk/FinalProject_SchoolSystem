package com.library.interfaces;

/**
 * Implemented by any component that can produce a human-readable report.
 */
public interface Reportable {

    /**
     * Builds and returns a textual report summarising the implementer's state.
     *
     * @return the formatted report
     */
    String generateReport();
}
