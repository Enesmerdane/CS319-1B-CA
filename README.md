# Cs319Bilkent

We have chosen Klaus Teuber's famous board game (Settlers of) Catan as our project for this course.

Brief description of the game Catan:

The objective of the game is to settle the island of Catan. There are nineteen hexagon-shaped tiles in this island, with eighteen of them being resource tiles and one being the desert tile. The tiles are placed randomly into the map, and each tile except for the desert tile is given a number from 2 to 12, excluding 7. The desert tile starts with a unit called the robber. The hexagon on which this robber stays can change, and that hexagon doesn't produce resources while the robber is there. Players can build settlements on the corners of the tiles, and roads to the edges of the tiles. The game starts with the players building one settlement and one road twice, first in a clockwise and then in a counter-clockwise rotation. 

Players roll a pair of dice at the start of their turns. If the result is 7, the player chooses a hexagon for the robber to go to, and then the robber steals resources from the players with settlements in the corners of that hexagon. If the result is another number, than the hexagons with that number produce one resource for each settlement in it's corners, which then are added to the players' hand (if the robber is not on that hexagon). Afterwards, players can use their resources to build a new settlement to a corner, to build a road, to upgrade their settlements to cities, or to draw a development card, which have different functions such as moving the robber or awarding a point. The players can also trade among themselves or with the bank, who can give one resource in exchange for four of another resource. This number can be changed if the player has a settlement near a harbor, which are specific points at the edges of the map.

The player who reaches 10 points wins the game and the game ends. Settlements award one point each, and cities award two points each. Each victory point development card grants one point, building a chain of roads with length greater than or equal to five grants three points to one player at a time, and drawing three Knight cards (development cards that let a player move the robber) also grants three points to one player at a time.

We will implement this game using Java. 

Google Docs Links:

Meeting Schedule: https://docs.google.com/document/d/1mj6myGd8StsufM98sZFAz4_Zc0bZ1pyuhizUyIXhvSA/edit?usp=sharing

First Iteration Analysis Report: https://docs.google.com/document/d/1Yc3Le2G_wbUrfOFEBRwgip0ZqssVStf_yGCa4HaIYz8/edit?usp=sharing
