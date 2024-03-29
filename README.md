<h1 align="center">OneList</h1>


### Design Document  

Tony Gentile, Ryan Durham, Logan Arnett, Justin Tracy

## Introduction 

Have you ever had trouble remembering what everyone in your family needs at the store? Have you ever forgotten to bring your shopping list with you? OneList can help by:

-	Saving your lists so that they can be accessed from anywhere
-	Sharing your lists with friends/family so anyone can add/remove items.
-	Create additional shopping lists and have them all in one place.

Never forget your shopping lists again, ensure nobody is forgotten when buying for your household, or make sure your business is always stocked up with OneList.

## Storyboard

![Storyboard](/Storyboard.png)

## Functional Requirements

### Scenario 1

**As a** member of a household,  
**I want** to be able to add and remove items from a grocery list,  
**So that** family members can know what I need.

#### Examples

##### 1.1
> **Given** a grocery list is setup,  
  **When** I click the add button,  
  **Then** a form appears with text fields to enter data about the item

##### 2.1
> **Given** item entry form has appeared,  
  **When** I enter apple information into the text fields, and click add button,  
  **Then** apples are added to the grocery list

##### 3.1
> **Given** a grocery list is populated with an item,  
  **When** I click the remove button on the item,  
  **Then** the item is removed from the grocery list
  
### Scenario 2

**As a** member of a household,  
**I want** to be able to check off items from a grocery list,  
**So that** family members know what has been purchased.

#### Examples

##### 2.1
> **Given** a categorized list,  
  **When** a user checks off items,  
  **Then** items are crossed out in the app and set as in cart

##### 2.2
> **Given** list items checked off,  
  **When** remove items button is clicked,  
  **Then** Items are moved to a list of purchased Items

## Class Diagram

![Class Diagram Image](/ClassDiagram.png)

## Class Diagram Description

##### MainActivity: The first screen the user sees. There will be all the lists the users is apart of on this screen, as well as a button to create new ones.
##### RetrofitInstance: Required class for Retrofit.
##### List: Stores all items added to list, in addtion to all users that are on the list.
##### User: The users that will belong to a list.
##### Item: Items that will populate the list.
##### Category: Referenced by items to further sort them (i.e. Food, Pharmaceutical, etc...).
##### IListDAO: Interface to store lists users are part of.
##### IUserDAO: Interface to store all users.
##### IITemDAO: Interface to store various items that can be added to lists.
##### ICategoryDAO: Interface to store all categories items can be a part of.

## Scrum Board / Product Backlog
https://github.com/orgs/OneList/projects/16

## Scrum Roles

| Role | Group Member |
|-|-|
| Product Owner | Tony Gentile |
| Development Team Member | Ryan Durham |
| Development Team Member | Logan Arnett |
| Scrum Master | Justin Tracy |
| Development Team Member | Nathan Kraft |

## Standup

### Saturdays at 3:00pm
