# Project: GearList

*This project is still in its early stage of development and is far from done. The main concept is established for now and is explained in this document.*

### The general concept

**Purpose**

When it comes to packing for a hiking trip, or other activities that require gear, we often repeat the same process. We often use the same list with a few modifications and this lead to having a lot of lists laying around, and this is where this program comes in. It holds a list of your gear with information about each item and allows you to create packing lists base upon your gear. In this way, you have everything involving packing in the same platform with the ability to print checklists and see how much your pack is going to weigh. 



### The program

The program consists of three windows. The first window you will meet is the gear list window seen in the image below. This window contains a full list of your added gear and provides two buttons one for adding items and one for remove the selected item. At the bottom of the window is a panel containing different statistics about your gear list. 

![](.\img\mainwindow.png)

When pressing the *New Item* button the window pictured below will appear. This window gives you the option to type in the details of an item and add it to your gear list.

![](.\img\additemwindow.png)

When pressing the *Open List Manager* button in the main window, the window pictured below will appear. This window is where you manage your packing lists. The left side of the window shows a list with all your packing lists and above that is buttons for adding and removing lists. The right side of the window displays your full gear list and by checking the box. To the right of each item is a checkbox used to choose which items are on the selected packing list. At the bottom, we again find a panel of statistics but this time only for the checked items within the selected list.

![](.\img\packinglistmanager.png)

#### Future work

**Checklist **

- The Packing List Manager will in the future provide two options for it to work as a checklist for when it comes to packing. You have the option to print it out or open another window that will act as a checklist.



*The overall user interface is still work in progress and changes will be made.*



#### Features

**Main window**

- Full gear list. *Showing information about each item.*
  - Button: New Item. *Opens the window for adding a new item to the full gear list.*
  - Button: Delete item. *Deletes the selected item from the full gear list.*
- Button: Open List Manager. *Opens the packing list manager window.*
- Statistics panel. *Displays statistics for the full gear list.*
  - Total price. *Total price of the full gear list.*
  - Total weight. *Total price of the full gear list.*

**Add item window**

- Input fields. 
  - Name of item.
  - Weight of item.
  - Brand.
  - Model.
  - Purchase location.
  - Price in DKK.
  - Note.
- Button: Add item. *Adds an item to the full gear list with the given information.*

**Packing List Manager**

- List of packing lists.
  - Button: New List. *Adds a new packing list.*
  - Button: Delete List. *Deletes the selected packing list.*
- List of packing list. *Items on the selected packing list or checked to the right.*
- Statistics panel. *Displays statistics for the selected packing list.*
  - Total price. *Total price of the selected packing list.*
  - Total weight. *Total weight of the selected packing list.*