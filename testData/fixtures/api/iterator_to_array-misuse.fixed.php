<?php

function cases_holder($source)
{
    $source->current();
    foreach ($source as $value) {}
    foreach ($source as $key => $value) {}

    (new $source())->current();
    ($source ?? $source)->current();

    /* false-positives: not the first element */
    iterator_to_array($source, false)[1];
}