package de.tarikweiss.deprecatednotice

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiNameIdentifierOwner
import org.jetbrains.kotlin.asJava.toLightAnnotation
import org.jetbrains.kotlin.psi.KtAnnotated

class KotlinDeprecatedInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is KtAnnotated) {
                    var foundDeprecated = false
                    element.annotationEntries.forEach {
                        println(it.toLightAnnotation()?.qualifiedName)
                        foundDeprecated = foundDeprecated || it.toLightAnnotation()?.qualifiedName == "kotlin.Deprecated"
                    }
                    if (!foundDeprecated) {
                        return
                    }

                    var elementToMark = element

                    var name = element.name ?: "Element"

                    if (element is PsiNameIdentifierOwner) {
                        elementToMark = element.identifyingElement ?: element
                    }

                    holder.registerProblem(elementToMark, "$name is deprecated. Consider using an adequate replacement.")
                }
                super.visitElement(element)
            }
        }
    }
}