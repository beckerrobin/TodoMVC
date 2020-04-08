# TodoMVC med backend

Projektet är baserat på Vue i frontend och använder hibernate för att kommunicera med databasen.

### Varför hibernate?
Jag valde hibernate för att det verkade vara det moderna och smidiga sättet och för att jag ville bli bättre på det.

### Skillnader mot UserStories
1. Jag valde att inte ha ett order-attribut på Tasks eftersom ordningen
ändå inte går att förändra och jag därför kan använda det genererade ID:t.
2. "isCompleted" har blivit "completed" för att passa in i namngivningsstandarden.
3. Det finns bara en lista (listId 1) eftersom UserStories-uppgiften skrevs innan jag visste vad projektet var. 

