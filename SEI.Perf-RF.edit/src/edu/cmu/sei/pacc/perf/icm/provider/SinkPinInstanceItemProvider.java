/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.cmu.sei.pacc.perf.icm.provider;


import edu.cmu.sei.pacc.perf.icm.IcmFactory;
import edu.cmu.sei.pacc.perf.icm.IcmPackage;
import edu.cmu.sei.pacc.perf.icm.SinkPinInstance;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link edu.cmu.sei.pacc.perf.icm.SinkPinInstance} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SinkPinInstanceItemProvider
  extends PinInstanceItemProvider
  implements	
    IEditingDomainItemProvider,	
    IStructuredItemContentProvider,	
    ITreeItemContentProvider,	
    IItemLabelProvider,	
    IItemPropertySource		
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SinkPinInstanceItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addModePropertyDescriptor(object);
      addReactSourcesPropertyDescriptor(object);
      addLinkSourcesPropertyDescriptor(object);
      addPriorityPropertyDescriptor(object);
      addDownsamplingFactorPropertyDescriptor(object);
      addMutexesPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Mode feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addModePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SinkPinInstance_mode_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_SinkPinInstance_mode_feature", "_UI_SinkPinInstance_type"),
         IcmPackage.Literals.SINK_PIN_INSTANCE__MODE,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the React Sources feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addReactSourcesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SinkPinInstance_reactSources_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_SinkPinInstance_reactSources_feature", "_UI_SinkPinInstance_type"),
         IcmPackage.Literals.SINK_PIN_INSTANCE__REACT_SOURCES,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Link Sources feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addLinkSourcesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SinkPinInstance_linkSources_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_SinkPinInstance_linkSources_feature", "_UI_SinkPinInstance_type"),
         IcmPackage.Literals.SINK_PIN_INSTANCE__LINK_SOURCES,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Priority feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPriorityPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SinkPinInstance_priority_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_SinkPinInstance_priority_feature", "_UI_SinkPinInstance_type"),
         IcmPackage.Literals.SINK_PIN_INSTANCE__PRIORITY,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Downsampling Factor feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addDownsamplingFactorPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SinkPinInstance_downsamplingFactor_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_SinkPinInstance_downsamplingFactor_feature", "_UI_SinkPinInstance_type"),
         IcmPackage.Literals.SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Mutexes feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addMutexesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_SinkPinInstance_mutexes_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_SinkPinInstance_mutexes_feature", "_UI_SinkPinInstance_type"),
         IcmPackage.Literals.SINK_PIN_INSTANCE__MUTEXES,
         true,
         false,
         true,
         null,
         null,
         null));
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(IcmPackage.Literals.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns SinkPinInstance.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/SinkPinInstance"));
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getText(Object object)
  {
    String label = ((SinkPinInstance)object).getName();
    return label == null || label.length() == 0 ?
      getString("_UI_SinkPinInstance_type") :
      getString("_UI_SinkPinInstance_type") + " " + label;
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(SinkPinInstance.class))
    {
      case IcmPackage.SINK_PIN_INSTANCE__MODE:
      case IcmPackage.SINK_PIN_INSTANCE__PRIORITY:
      case IcmPackage.SINK_PIN_INSTANCE__DOWNSAMPLING_FACTOR:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case IcmPackage.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    newChildDescriptors.add
      (createChildParameter
        (IcmPackage.Literals.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION,
         IcmFactory.eINSTANCE.createConstant()));

    newChildDescriptors.add
      (createChildParameter
        (IcmPackage.Literals.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION,
         IcmFactory.eINSTANCE.createExponential()));

    newChildDescriptors.add
      (createChildParameter
        (IcmPackage.Literals.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION,
         IcmFactory.eINSTANCE.createNormal()));

    newChildDescriptors.add
      (createChildParameter
        (IcmPackage.Literals.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION,
         IcmFactory.eINSTANCE.createUniform()));

    newChildDescriptors.add
      (createChildParameter
        (IcmPackage.Literals.SINK_PIN_INSTANCE__EXEC_TIME_DISTRIBUTION,
         IcmFactory.eINSTANCE.createUnknown()));
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return ICMEditPlugin.INSTANCE;
  }

}
