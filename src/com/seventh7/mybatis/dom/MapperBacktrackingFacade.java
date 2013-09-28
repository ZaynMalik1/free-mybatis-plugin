package com.seventh7.mybatis.dom;

import com.google.common.base.Optional;

import com.intellij.psi.PsiClass;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import com.seventh7.mybatis.dom.model.Association;
import com.seventh7.mybatis.dom.model.Collection;
import com.seventh7.mybatis.dom.model.ResultMap;

/**
 * @author yanglin
 */
public final class MapperBacktrackingFacade {

  private MapperBacktrackingFacade() {
    throw new UnsupportedOperationException();
  }

  /**
   * TODO refactor to make it much more applicable
   */
  public static Optional<PsiClass> getResultPropertyClzz(XmlAttributeValue attributeValue) {
    DomElement domElement = DomUtil.getDomElement(attributeValue);
    if (null == domElement) {
      return Optional.absent();
    }
    Collection collection = DomUtil.getParentOfType(domElement, Collection.class, true);
    if (null != collection) {
      return Optional.fromNullable(collection.getOfType().getValue());
    }
    Association association = DomUtil.getParentOfType(domElement, Association.class, true);
    if (null != association) {
      return Optional.fromNullable(association.getJavaType().getValue());
    }
    ResultMap resultMap = DomUtil.getParentOfType(domElement, ResultMap.class, true);
    if (null != resultMap) {
      return Optional.fromNullable(resultMap.getType().getValue());
    }
    return Optional.absent();
  }

}