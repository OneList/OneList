<h1 align="center">OneList</h1>


Design Document  

Tony Gentile, Ryan Durham, Logan Arnett, Justin Tracy, Nathan Kraft

## Introduction 

Have you ever had trouble remembering what everyone in your family needs at the store? Have you ever forgotten to bring your shopping list with you? OneList can help by:

-	Saving your lists so that they can be accessed from anywhere
-	Sharing your lists with friends/family so anyone can add/remove items.
-	Create additional shopping lists and have them all in one place.

Never forget your shopping lists again, ensure nobody is forgotten when buying for your household, or make sure your business is always stocked up with OneList.

## Storyboard

```diff
- add storyboard here
```

## Functional Requirements

### Scenario 1

**As a** member of a family,  
**I want** to be able to add or remove items from a grocery list,  
**So that** family members can know what I need.

#### Examples

##### 1.1
> **Given** a grocery list is setup,  
  **When** I add apples to the list  
  **Then** apples are listed on the grocery list

##### 2.1
> **Given** a grocery list is setup  
  **When** I remove apples from the list  
  **Then** Apples are no longer in the list
  
### Scenario 2

**As a** member of a family,  
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
  
  ### Scenario 3

**As a** member of a household
**I want** to be able to add items to a grocery list 
**So that**  I can get my groceries when someone in the household goes shopping 

#### Examples

##### 3.1
> **Given** I have a group grocery list,  
  **When** enter in item name, item description(optional), click "+" icon,  
  **Then** grocery item added to list

##### 3.2
> **Given** item form has appeared,  
  **When** remove items button is clicked,  
  **Then** Items are moved to a list of purchased Items
  
 ##### 3.3
> **Given** I have a populated grocery list,  
  **When** I click "-" icon next to item,  
  **Then** item is removed from list

```diff
- add more scenarios here
```

## Class Diagram

```diff
- add class diagram here
```

## Scrum Roles

```diff
- set scrum roles here:
```

| Role | Group Member |
|-|-|
|  | Tony Gentile |
|  | Ryan Durham |
|  | Logan Arnett |
|  | Justin Tracy |
|  | Nathan Kraft |

## Standup

```diff
- Add weekly standup time here:
```
