package de.tarikweiss.deprecatednotice

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.php.lang.psi.elements.PhpNamedElement


class PhpStormDeprecatedInspection : LocalInspectionTool() {

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is PhpNamedElement)                {
                    if (!element.isDeprecated) {
                        return
                    }

                    val identifyingElement = element.identifyingElement ?: return

                    holder.registerProblem(identifyingElement, "'${element.name}' is deprecated. Consider using an adequate replacement.")
                }
                super.visitElement(element)
            }
        }
    }
}