package com.diabin.latte.compiler;

import javax.annotation.processing.Filer;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public class EntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void>{

    private final Filer FILER;
    private TypeMirror mTypeMirror = null;
    private String mPackageName = null;

    EntryVisitor(Filer FILER) {
        this.FILER = FILER;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        generateJavaCode(t);
        return p;
    }

    private void generateJavaCode(TypeMirror typeMirror){

    }
}
