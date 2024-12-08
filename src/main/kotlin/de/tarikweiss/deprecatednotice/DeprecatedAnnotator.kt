package de.tarikweiss.deprecatednotice

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.jetbrains.php.lang.psi.elements.Method

class DeprecatedAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        println(element.javaClass.name)
        if (element !is Method) {
            return
        }

        if (!element.isDeprecated) {
            return
        }

        val identifyingElement = element.identifyingElement

        identifyingElement?.let {
            holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "This method is deprecated")
                .range(identifyingElement)
                .highlightType(ProblemHighlightType.LIKE_MARKED_FOR_REMOVAL)
                .create()
        }
    }
}