package com.advalent.automation.groovy.module

import java.util.function.Predicate

interface IModuleInformer {

    /**
     * get the module satisfying given condition
     *
     * @param the condition to satisfy
     * @return module that saatisfy the given condition
     * */
    Module getModule(Predicate<Module> condition)

}

