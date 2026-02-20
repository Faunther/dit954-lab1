Analysera de beroenden som finns med avseende på cohesion och coupling, och
Dependency Inversion Principle.

1. Vilka beroenden är nödvändiga?
2. Vilka klasser är beroende av varandra som inte borde vara det?
3. Finns det starkare beroenden än nödvändigt?
4. Kan ni identifiera några brott mot övriga designprinciper vi pratat om i
   kursen?

- Beroenden mellan Car och alla enskilda bilar är optimalt (ModelXYZ <: Car)
  - Inga bilar relaterar till varandra
- Relationen mellan Volvo240 och TimerListener finns. Känns vid första anblick
  något dumt då de inte bör vara särskilt relevanta (låg cohesion). Däremot är
  det bara TimerListener som beror på Volvo240, inte tvärt om, vilket kan ses
  som en klient som använder en datamodell. Att undvika denna dependency skulle
  även innebära stora mängder abstraktioner och ökad komplexitet, med låg nytta.
- Alla bilar implementerar parallellt CarData, samtidigt som Car "wrap:ar" alla
  metoder i CarData för att exponera
  - Pro: Minskar coupling då andra kan få e.x. engine power utan att använda
    CarData
  - Con: Ökad komplexitet. Framförallt vid extra data som CarHaulerData (vilket
    ännu inte är ett superstort problem, men exempelvis Scania hade kunnat haft
    mer data - liftMaxAngle, liftMinAngle, osv.)
  - Con: Icke-DRY - varje bil-klass skapar sitt egna CarData-subklass som sedan
    skapas som ett statiskt object som sedan i konstruktorn läggs till som
    icke-static fält. (detta för att skapa en form av ärvda statiska fält)
  - Bör antagligen bytas ut mot metoder som overridas för minskad komplexitet
- CarView bör ha hand om all grafik - därmed alla dependencies med
  Swing-klasser. Samma gäller för DrawPanel. I dagsläget stämmer detta, med
  undantag för CarController som (via TimerListener) använder både JFrame och
  ActionListener. Att en controller använder en ActionListener är som sig bör -
  men JFrame relationen bör undvikas då Controllerns jobb inte är att hantera
  grafiken.
  - Största problemet just nu är dock att CarController använder DrawPanel,
    genom CarView, för att få storleken på bilarna, workshoppen samt
    "spelplanen". Detta bör eventuellt abstraheras bort för att CarController
    endast ska ha relation med CarView, och vara oberoende av hur renderingen av
    bilarna är implementerad.
    - Eventuellt: Wrapper (/Decorator?) som har koll på Car+Image. Ökar dock
      komplexitet i utbyte mot att både CarView och CarController båda har
      relation till den, och därav minskar komplexiteten endast marginellt.

Analysera era klasser med avseende på Separation of Concern (SoC) och Single
Responsibility Principle (SRP).

1. Vilka ansvarsområden har era klasser?
2. Vilka anledningar har de att förändras?
3. På vilka klasser skulle ni behöva tillämpa dekomposition för att bättre följa
   SoC och SRP?

- Varje Car-subtyp har stor anledning att förändras. Så fort en biltillverkare
  ändrar en modell's egenskaper kommer klasserna att behöva ändras. Dock behöver
  Car nästan aldrig ändras - då konceptet Bil sällan förändras.
- Alla bilar är endast model-klasser, dvs. hanterar endast deras data (position,
  vinkel, bilar på flak, osv.). De har inga utåtgående relationer till resten av
  kodbasen - vilket tyder på att detta stämmer.
-

## TODO till diagrammet:

- Bör ha en pil mellan CarController och DrawPanel då CarController använder
  DrawPanel via CarView
