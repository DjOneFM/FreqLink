package beat_link_trigger;

import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.SubstanceColorSchemeBundle;
import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.colorscheme.BaseColorScheme;
import org.pushingpixels.substance.api.colorscheme.LightGrayColorScheme;
import org.pushingpixels.substance.api.painter.border.ClassicBorderPainter;
import org.pushingpixels.substance.api.painter.decoration.MatteDecorationPainter;
import org.pushingpixels.substance.api.painter.fill.FractionBasedFillPainter;
import org.pushingpixels.substance.api.painter.highlight.FractionBasedHighlightPainter;
import org.pushingpixels.substance.api.painter.overlay.BottomLineOverlayPainter;
import org.pushingpixels.substance.api.painter.overlay.TopLineOverlayPainter;
import org.pushingpixels.substance.api.shaper.ClassicButtonShaper;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.SubstanceSlices.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceSlices.ColorSchemeAssociationKind;

import java.awt.Color;

/**
 * FreqMode skin for FreqLink — deep dark background with Pioneer-orange accents.
 *
 * FreqMode color palette:
 *   Background:  #121212  (near-black)
 *   Panel:       #1E1E1E  (dark surface)
 *   Accent:      #FF6600  (Pioneer orange)
 *   Active/On:   #FF8800  (bright orange)
 *   Text:        #F0F0F0  (near-white)
 *   Muted text:  #888888  (mid-gray)
 *   Border:      #2A2A2A  (subtle border)
 */
public class FreqModeSkin extends SubstanceSkin {

    public static final String NAME = "FreqMode";

    /** FreqMode ultra-dark background scheme */
    public static class FreqModeUltraDarkScheme extends BaseColorScheme {
        private static final Color ULTRA_DARK  = new Color(0x0A, 0x0A, 0x0A);
        private static final Color DARK        = new Color(0x12, 0x12, 0x12);
        private static final Color MID         = new Color(0x1E, 0x1E, 0x1E);
        private static final Color LIGHT       = new Color(0x2A, 0x2A, 0x2A);
        private static final Color EXTRA_LIGHT = new Color(0x38, 0x38, 0x38);
        private static final Color FOREGROUND  = new Color(0xF0, 0xF0, 0xF0);

        public FreqModeUltraDarkScheme() {
            super("FreqMode Ultra Dark", true);
        }
        public Color getUltraDarkColor()  { return ULTRA_DARK; }
        public Color getDarkColor()        { return DARK; }
        public Color getMidColor()         { return MID; }
        public Color getLightColor()       { return LIGHT; }
        public Color getExtraLightColor()  { return EXTRA_LIGHT; }
        public Color getUltraLightColor()  { return EXTRA_LIGHT; }
        public Color getForegroundColor()  { return FOREGROUND; }
        public Color getBackgroundFillColor() { return DARK; }
        public Color getFocusRingColor()   { return new Color(0xFF, 0x66, 0x00); } // orange focus ring
        public Color getLineColor()        { return LIGHT; }
        public Color getSelectionBackgroundColor() { return new Color(0xFF, 0x66, 0x00); }
        public Color getSelectionForegroundColor() { return Color.WHITE; }
        public Color getTextBackgroundFillColor()  { return MID; }
    }

    /** FreqMode orange accent scheme (used for highlights & active states) */
    public static class FreqModeOrangeScheme extends BaseColorScheme {
        private static final Color ORANGE_DARK  = new Color(0xCC, 0x44, 0x00);
        private static final Color ORANGE_MID   = new Color(0xFF, 0x66, 0x00);
        private static final Color ORANGE_LIGHT = new Color(0xFF, 0x88, 0x00);
        private static final Color ORANGE_PALE  = new Color(0xFF, 0xAA, 0x44);
        private static final Color FOREGROUND   = Color.WHITE;

        public FreqModeOrangeScheme() {
            super("FreqMode Orange", false);
        }
        public Color getUltraDarkColor()  { return new Color(0x99, 0x33, 0x00); }
        public Color getDarkColor()        { return ORANGE_DARK; }
        public Color getMidColor()         { return ORANGE_MID; }
        public Color getLightColor()       { return ORANGE_LIGHT; }
        public Color getExtraLightColor()  { return ORANGE_PALE; }
        public Color getUltraLightColor()  { return new Color(0xFF, 0xCC, 0x99); }
        public Color getForegroundColor()  { return FOREGROUND; }
        public Color getBackgroundFillColor() { return ORANGE_MID; }
        public Color getFocusRingColor()   { return ORANGE_LIGHT; }
        public Color getLineColor()        { return ORANGE_DARK; }
        public Color getSelectionBackgroundColor() { return ORANGE_LIGHT; }
        public Color getSelectionForegroundColor() { return Color.WHITE; }
        public Color getTextBackgroundFillColor()  { return ORANGE_MID; }
    }

    public FreqModeSkin() {
        super();

        FreqModeUltraDarkScheme darkScheme   = new FreqModeUltraDarkScheme();
        FreqModeOrangeScheme    orangeScheme = new FreqModeOrangeScheme();

        // --- Default (enabled) bundle ---
        SubstanceColorSchemeBundle defaultBundle = new SubstanceColorSchemeBundle(
                orangeScheme,   // active scheme  (selected/pressed components)
                darkScheme,     // enabled scheme (normal components)
                darkScheme      // disabled scheme
        );

        // Highlight (hover) uses a slightly lighter dark with orange border
        defaultBundle.registerColorScheme(orangeScheme,
                ColorSchemeAssociationKind.HIGHLIGHT,
                ComponentState.SELECTED,
                ComponentState.ROLLOVER_SELECTED);

        defaultBundle.registerColorScheme(darkScheme,
                ColorSchemeAssociationKind.HIGHLIGHT,
                ComponentState.ROLLOVER_UNSELECTED);

        // Borders — subtle except when active
        defaultBundle.registerColorScheme(darkScheme,
                ColorSchemeAssociationKind.BORDER,
                ComponentState.ENABLED);
        defaultBundle.registerColorScheme(orangeScheme,
                ColorSchemeAssociationKind.BORDER,
                ComponentState.SELECTED,
                ComponentState.ROLLOVER_SELECTED,
                ComponentState.PRESSED_SELECTED,
                ComponentState.PRESSED_UNSELECTED);

        registerDecorationAreaSchemeBundle(defaultBundle, DecorationAreaType.NONE);

        // --- Header / toolbar area ---
        SubstanceColorSchemeBundle headerBundle = new SubstanceColorSchemeBundle(
                orangeScheme, darkScheme, darkScheme);

        registerDecorationAreaSchemeBundle(headerBundle,
                new FreqModeUltraDarkScheme(),
                DecorationAreaType.PRIMARY_TITLE_PANE,
                DecorationAreaType.SECONDARY_TITLE_PANE,
                DecorationAreaType.HEADER,
                DecorationAreaType.TOOLBAR,
                DecorationAreaType.FOOTER);

        // --- Painters ---
        setSelectedTabFadeStart(0.1);
        setSelectedTabFadeEnd(0.3);

        FractionBasedFillPainter fillPainter = new FractionBasedFillPainter(
                "FreqMode Fill",
                new float[]{ 0.0f, 0.5f, 1.0f },
                new org.pushingpixels.substance.api.colorscheme.ColorSchemeSingleColorQuery[]{
                        scheme -> scheme.getUltraDarkColor(),
                        scheme -> scheme.getDarkColor(),
                        scheme -> scheme.getMidColor()
                });
        registerPainter(SubstanceSkin.PainterKind.FILL, fillPainter);

        MatteDecorationPainter decorationPainter = new MatteDecorationPainter();
        registerPainter(SubstanceSkin.PainterKind.DECORATION, decorationPainter);

        ClassicBorderPainter borderPainter = new ClassicBorderPainter();
        registerPainter(SubstanceSkin.PainterKind.BORDER, borderPainter);

        FractionBasedHighlightPainter highlightPainter = new FractionBasedHighlightPainter(
                "FreqMode Highlight",
                new float[]{ 0.0f, 0.5f, 1.0f },
                new org.pushingpixels.substance.api.colorscheme.ColorSchemeSingleColorQuery[]{
                        scheme -> scheme.getLightColor().darker(),
                        scheme -> scheme.getLightColor(),
                        scheme -> scheme.getLightColor().darker()
                });
        registerPainter(SubstanceSkin.PainterKind.HIGHLIGHT, highlightPainter);

        registerPainter(SubstanceSkin.PainterKind.OVERLAY,
                new BottomLineOverlayPainter(
                        scheme -> scheme.getUltraDarkColor()),
                DecorationAreaType.PRIMARY_TITLE_PANE,
                DecorationAreaType.SECONDARY_TITLE_PANE,
                DecorationAreaType.HEADER,
                DecorationAreaType.TOOLBAR);

        registerPainter(SubstanceSkin.PainterKind.OVERLAY,
                new TopLineOverlayPainter(
                        scheme -> scheme.getExtraLightColor().darker()),
                DecorationAreaType.FOOTER);

        setButtonShaper(new ClassicButtonShaper());
    }

    @Override
    public String getDisplayName() {
        return NAME;
    }
}
