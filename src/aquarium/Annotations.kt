package aquarium

import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation

@ImAPlant class Plant {
    fun trim() {}
    fun fertilize() {}

    @get:OnGet
    val isGrowing: Boolean = true

    @set:OnSet
    var needsFood: Boolean = false
}

annotation class ImAPlant

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class OnGet

@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class OnSet

fun reflections() {
    var classObj = Plant::class

    // print all member functions
    for (method in classObj.declaredMemberFunctions) {
        println(method.name)
    }

    // print all annotations
    for (annotation in classObj.annotations) {
        println(annotation.annotationClass.simpleName)
    }

    // find one annotation, or null
    val annotated = classObj.findAnnotation<ImAPlant>()
    annotated?.apply {
        println("found a plant annotation!")
    }
}
