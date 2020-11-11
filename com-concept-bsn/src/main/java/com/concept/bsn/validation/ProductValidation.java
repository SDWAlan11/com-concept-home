package com.concept.bsn.validation;

import com.concept.test.data.entity.Product;

@FunctionalInterface
public interface ProductValidation {

    void validate(final Product product);
}