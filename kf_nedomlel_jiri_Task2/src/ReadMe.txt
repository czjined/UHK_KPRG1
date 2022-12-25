
Popis funkce a ovládání programu

Ovládání

    Vykreslení úsečky

        Začátek úsečky se  volí kliknutím levým tlačítkem (LeftButton - LB) do rastru a poté táhnutím při stisknutém LB se
        vykresluje k aktuální poloze myši.
        Uvolněním LB se zadá koncový bod úsečky, která zůstane vykreslena.

        Naprosto shodným způsobem, pouze se změnou využití pravého tlačítka myši se vykresluje čárkovaná úsečka.

    Polygon

        Vrcholy polygonu se zadávají postupným klikáním LB a tyto body se okamžitě vykreslují zeleně.

        Vykreslení polygonu tlačítkem P.

        Vyplnění polygonu:

            Seed fill - LB + Shift; vyplní celou oblast dle barvy pozadí, tzn. uvnitř polygonu pouze jeho příslušnou
                        ohraničenou část.

            Scan line - tlačítkem F; default nastaveno vodorovné šrafování vykreslením každé desáté řádky.
                         Hustotu šrafování lze ručně nastavit parametrem Srafa při volání metody fillHorizontal
                         ( plné vybarvení polygonu při srafa = 1).


    Vyčištění plátna tlačítkem C, kdy se vymažou i dosud zadané vrcholy polygonu.


 Struktura programu

    Pro obě úlohy (Task1 i Task2) se používá pouze předpřipravený modul Task2, který je přejmenovaný dle Olivy na
    kf_nedomlel_jiri_Task2.

    Veškeré ovládání obstarává připravená třída Control2D.

    Vykreslení úsečky metodou triviálního vykreslování zajišťuje třída TrivialLineRasterizer, kde samotné vykreslení
    provádí metoda rasterize místo drawLine (dle příkladů ve videu ze cvičení).

    Každý způsob vyplnění polygonu má svoji vlastní třídu SeedFill x ScanLine.


  GitHub: https://github.com/czjined/UHK_KPRG1.git
