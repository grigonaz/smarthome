# SmartHome

**Funkční požadavky** *(podle zadání)*

F1: Všechny třídy, se kterými pracujeme jsou ve složce "model".  
F2: Všechna zařízení mají API na ovládání. Devicy mají API na ovladaní přimo ve svých třídách. "Door" a "Ventilation" se ovládájí pomocí třídy "Controller".  
F3: Spotřebiče mají svojí spotřebu ve třech stavech a to jsou *ON*, *IDLE* a *OFF*. Na regulování těchto stavů použiváme desigh pattern "State Machine", který se nachazí ve složce "devices".  
F4: Třídy, které jsou ve složce "meter", mají API na sběr dat o sobě.  
F5: Každou třída, která extenduje třídu "Device", osoby a zviířata mohou ovlivňovat pomocí speciálních funkcí, které jsou v těch třídách.  
F6: Veškerá logika, která je spojená s eventemi, nacháží ve složce "alerts_and_events". Eventy a alerty se generuje v třídě "Creature", v trídách, které se od třídy "Creature" dědí, a to jsou "Person" a "Animal", v třídě "Device" a v trídách, které se od třídy "Device" dědí.  
F7: Alerty odstraňují lidé, ale také jsou v projetktu několik tříd, které odstraňují alerty automatický a pak vykonují nějakou činnost. (*Např.: trída "Louver" zavírá žaluzie, když je venkku fouká silný vitr*)  
F8: Trídy, které se nachází ve složce "report", generují různé reporty. Funkce main() ve hlavní třídě vypisuje ty reporty buď to do příkazového řádku, nebo do speciálního souboru, který se generuje automatický.  
F9: Je funkce handleAlert() ve třídě "Person" ve složce "creature", která při rozbití zařízení posílá notification a person dostává manual.  
F10: Ve třídě "Person" jsou proměnné deviceUsageNumber a sportEquipmentUsageNumbe, které jsou zodpovědné za použití deviců nebo sportovního vybavení. To všechno kontroluje funkce nextAction(), která je taky ve třídě "Person".  


**Design patterns**

SINGLETON  
Třída "Controller" vytvořena právě jednou, jakožto jediny controller v budově.


OBSERVER  
Jednotlivé spotřebiče "Device" si udržují seznam observerů – senzorů, které notifikuje, pokud dojde 
ke změně stavu spotřebiče, jako je zapnutí, vypnutí a podobě.


STATE MACHINE  
Slouží při používání spotřebiců k pohybu mezi stavy *ON*, *IDLE* a *OFF*.


**Nefunkční požadavky**
- Není autentizace ani autorizace.
- Běží pouze v jedné JVM.
- JavaDoc je v kořenu repozitáře.
- Reporty jsou generovány do textového souboru v SmartHomeOMO.
- Konfigurace domu, zařízení a obyvatel domu nahrani z třidy Configuration a pomoci metod configurationOne() a configurationTwo().

**Požadované vystupy**
  
- Všechny diagramy jsou v hlavní složce projetku na GITu (UML a Use Case).
- Podrobní JavaDoc je taky v hlavní složce projetku na GITu.
- Ve třidě "Configuration" je funkce, která obsahuje dvě konfigurace. Soubor s vygenerovanými konfiguracemi stejně jako minule vystupy leží v hlavní složce projetku na GITu.
