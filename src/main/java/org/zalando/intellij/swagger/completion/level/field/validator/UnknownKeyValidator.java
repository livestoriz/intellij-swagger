package org.zalando.intellij.swagger.completion.level.field.validator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.zalando.intellij.swagger.completion.level.field.Field;

import java.util.List;

public class UnknownKeyValidator implements Validator {

    private static final String VENDOR_EXTENSION_PREFIX = "x-";

    @Override
    public void validate(final String key,
                         final List<Field> availableKeys,
                         final PsiElement psiElement,
                         final AnnotationHolder annotationHolder) {
        boolean keyFoundInAvailableKeys = availableKeys.stream().anyMatch(field -> field.getName().equals(key));
        if (!keyFoundInAvailableKeys && !key.startsWith(VENDOR_EXTENSION_PREFIX)) {
            annotationHolder.createErrorAnnotation(psiElement, "Invalid key");
        }
    }
}