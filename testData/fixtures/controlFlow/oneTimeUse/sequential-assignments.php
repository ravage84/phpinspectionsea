<?php

function cases_holder() {
    <warning descr="Variable $variable is redundant.">$variable</warning> = $variant;
    $variable = $variable ?? $alternative;

    <warning descr="Variable $variable is redundant.">$variable</warning> = $variant;
    $variable = $variable ?: $alternative;
}

function false_positives_holder() {
    /** @var null|object $variable */
    $variable = $variant;
    $variable = $variable ?? $alternative;

    /** @var null|object $variable */
    $variable = $variant;
    $variable = $variable ?: $alternative;
}