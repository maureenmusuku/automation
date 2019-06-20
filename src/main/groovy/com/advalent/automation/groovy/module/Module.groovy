package com.advalent.automation.groovy.module

import com.advalent.automation.impl.utils.AlphanumComparator
import com.google.common.base.Preconditions

/**
 * @author sshrestha
 */

class Module implements Comparable<Module> {
    String name
    String linkId
    String pageTitle
    Module parent

    List<Module> children


    boolean hasParent() {
        return parent != null
    }

    boolean hasChildren() {
        return children != null && !children.isEmpty()
    }

    void addChild(Module childModule) {
        if (children == null) {
            children = new ArrayList<>()
        }
        children.add(childModule)
        childModule.parent = this
    }

    boolean isSection() {
        return hasChildren()
    }

    boolean isLandingPage() {
        return pageId && pageTitle
    }

    public static ModuleBuilder builder() {
        return new ModuleBuilder()
    }



    public List<Module> getLeafModules() {
        return getLeafModulesRecursive(this);
    }


    private List<Module> getLeafModulesRecursive(Module module) {
        if (!module.hasChildren()) {
            return Arrays.asList(module);
        }
        List<Module> leafModules = new ArrayList<>();
        for (Module subModule : module.getChildren()) {
            leafModules.addAll(getLeafModulesRecursive(subModule));
        }
        return leafModules;
    }

    boolean isLeaf() {
        return !hasChildren();
    }

    @Override
    int compareTo(Module other) {
        Preconditions.checkNotNull(other)

        String firstText = linkId.replaceAll("[\\W]", "").toLowerCase();
        String secondText = other.linkId.replaceAll("[\\W]", "").toLowerCase();
        return new AlphanumComparator().compare(firstText, secondText);
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", linkId='" + linkId + '\'' +
                ", pageTitle='" + pageTitle + '\'' +
                ", parent=" + parent +
                ", children=" + children +
                '}';
    }
}