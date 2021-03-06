package com.diabin.latte.compiler;

import com.diabin.latte.annotations.AppRegisterGenerator;
import com.diabin.latte.annotations.EntryGenerator;
import com.diabin.latte.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SuppressWarnings("unused")
@AutoService(Processor.class)
final public class LatteProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        final Set<Class<?> extends Annotation> supportAnnotations
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportAnnotations();
        for (Class<? extends Annotation> annotations : supportAnnotations){
            types.add(annotations.getCanonicalName());

        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations(){
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor){
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)){
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMirrors){
                final Map<?extends ExecutableElement,?extends AnnotationValue>  elementValues =
                        annotationMirror.getElementValues();
                for (Map.Entry<?extends ExecutableElement,?extends AnnotationValue> entry
                        :elementValues.entrySet()){
                    entry.getValue().accept(visitor,null);
                }
            }
        }
    }

    private void generateEntryCode(RoundEnvironment env){
        final EntryVisitor mEntryVisitor = new EntryVisitor(processingEnv.getFiler());
        scan(env,EntryGenerator.class,mEntryVisitor);

    }

    private void generatePayEntryCode(RoundEnvironment env){
        final PayEntryVisitor mEntryVisitor = new PayEntryVisitor(processingEnv.getFiler());
        scan(env,PayEntryGenerator.class,mEntryVisitor);

    }

    private void generateAppRegisterCode(RoundEnvironment env){
        final AppRegisterVisitor mEntryVisitor = new AppRegisterVisitor(processingEnv.getFiler());
        scan(env,AppRegisterGenerator.class,mEntryVisitor);

    }


}
