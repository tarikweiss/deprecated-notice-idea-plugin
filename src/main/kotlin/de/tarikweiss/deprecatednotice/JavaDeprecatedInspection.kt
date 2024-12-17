package de.tarikweiss.deprecatednotice

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.*

class JavaDeprecatedInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is PsiJvmModifiersOwner) {
                    if (!element.hasAnnotation("java.lang.Deprecated")) {
                        return
                    }

                    var elementToMark = element

                    var name = "Element"

                    if (element is PsiNameIdentifierOwner) {
                        name = element.name ?: name
                        elementToMark = element.identifyingElement ?: element
                    }

                    holder.registerProblem(elementToMark, "$name is deprecated. Consider using an adequate replacement.")
                }
                super.visitElement(element)
            }
        }
    }
}