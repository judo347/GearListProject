# Project: GearList

*This project is still in its early stage of development and is far from done. The main concept is established for now and is explained in this document.*

### The general concept

bnlag blag blag..... introduction.....



#### The program

The program consists of three windows. The first window you will meet is the gear list window seen in the image below. This window contains a full list of your added gear, and provides two buttons one for adding items and one for remove the selected item. At the bottom of the window is a panel containing different statistics about your gear list. 

![](.\img\mainwindow.png)

When pressing the *New Item* button the window pictured below will appear. This window enables you to type in the details of an item and add it to your gear list.

![](.\img\additemwindow.png)

When pressing the *Open List Manager* button in the main window, the window pictured below will appear. This window is where you manage your packing lists. The left side of the window shows a list with all our packing lists and the above that is buttons for adding and removing lists. The right side of the window displays your full gear list and by checking the box on the right side of the items will add the items to the current selected list. At the bottom we again find a panel of statistics but this time only for the checked items within the selected list.

![](.\img\packinglistmanager.png)



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