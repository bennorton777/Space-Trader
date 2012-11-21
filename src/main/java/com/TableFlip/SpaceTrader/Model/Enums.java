package com.TableFlip.SpaceTrader.Model;

/**
 * Centralized Enums location
 */
public class Enums {
    public enum Skill {
        FIGHTER, PILOT, ENGINEER, TRADER
    }

    public enum TechLevel{
        PREAGRICULTURE ("Pre-Agricultural"), AGRICULTURE ("Agrarian"), MEDIEVAL ("Medieval"), RENAISSANCE ("Renaissance"), EARLY ("Low Tech"), INDUSTRIAL ("Industrial"), POSTINDUSTRIAL ("Post-Industrial"), HITECH ("High-Tech");
        private final String _TEXT;
        TechLevel (String text) {
            _TEXT = text;
        }
        public String getText() {
            return _TEXT;
        }
    }

    public enum Resources{
        NOSPECIALRESOURCES ("Nothing"), MINERALRICH ("Mineral-Rich"), MINERALPOOR ("Mineral-Poor"), WHYISTHERUMGONE ("Why Is The Rum Gone?"), LOTSOFRUM ("So Much Rum!"), RICHSOIL ("Fertile Soil"), POORSOIL ("Barren Soil"), RICHFAUNA ("Rich Fauna"), LIFELESS ("Lifeless"), WEIRDMUSHROOMS ("Strange Mushrooms"),
        LOTSOFHERBS ("Lots of Herbs"), ARTISTIC ("Artistic"), WARLIKE ("Warlike");
        private final String _TEXT;
        Resources (String text) {
            _TEXT = text;
        }
        public String getText() {
            return _TEXT;
        }
    }
    public enum MarketValues{
        PRICE, QUANTITY
    }
}
