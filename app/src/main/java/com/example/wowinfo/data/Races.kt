import com.example.wowinfo.R
import com.example.wowinfo.model.Race

object RaceList {

    private val alliance = FactionList.factions[0]
    private val neutral = FactionList.factions[1]
    private val horde = FactionList.factions[2]

    val allianceRaces: List<Race> = listOf(
        Race(
            name = R.string.human,
            faction = alliance,
            crest = R.drawable.crest_alliance_human,
            description = R.string.human_description,
        ),
        Race(
            name = R.string.dwarf,
            faction = alliance,
            crest = R.drawable.crest_alliance_dark_iron_dwarf,
            description = R.string.dwarf_description,
        ),
        Race(
            name = R.string.night_elf,
            faction = alliance,
            crest = R.drawable.crest_alliance_night_elf,
            description = R.string.night_elf_description,
        ),
        Race(
            name = R.string.gnome,
            faction = alliance,
            crest = R.drawable.crest_alliance_gnome,
            description = R.string.gnome_description,
        ),
        Race(
            name = R.string.draenei,
            faction = alliance,
            crest = R.drawable.crest_alliance_draenei,
            description = R.string.draenei_description,
        ),
        Race(
            name = R.string.worgen,
            faction = alliance,
            crest = R.drawable.crest_alliance_worgen,
            description = R.string.worgen_description,
        ),
        Race(
            name = R.string.void_elf,
            faction = alliance,
            crest = R.drawable.crest_alliance_void_elf,
            description = R.string.void_elf_description
        ),
        Race(
            name = R.string.lightforged_draenei,
            faction = alliance,
            crest = R.drawable.crest_alliance_lightforged_draenei,
            description = R.string.lightforged_draenei_description
        ),
        Race(
            name = R.string.dark_iron_dwarf,
            faction = alliance,
            crest = R.drawable.crest_alliance_dark_iron_dwarf,
            description = R.string.dark_iron_dwarf_description
        ),
        Race(
            name = R.string.kul_tiran,
            faction = alliance,
            crest = R.drawable.crest_alliance_kul_tiran,
            description = R.string.kul_tiran_description
        ),
        Race(
            name = R.string.mechagnome,
            faction = alliance,
            crest = R.drawable.crest_alliance_mechagnome,
            description = R.string.mechagnome_description
        ),
    )

    val neutralRaces: List<Race> = listOf(
        Race(
            name = R.string.pandaren,
            faction = neutral,
            crest = R.drawable.crest_neutral_pandaren,
            description = R.string.pandaren_description
        ),
        Race(
            name = R.string.dracthyr,
            faction = neutral,
            crest = R.drawable.crest_neutral_dracthyr,
            description = R.string.dracthyr_description
        ),
    )

    val hordeRaces: List<Race> = listOf(
        Race(
            name = R.string.orc,
            faction = horde,
            crest = R.drawable.crest_horde_orc,
            description = R.string.orc_description,
        ),
        Race(
            name = R.string.undead,
            faction = horde,
            crest = R.drawable.crest_horde_undead,
            description = R.string.undead_description,
        ),
        Race(
            name = R.string.tauren,
            faction = horde,
            crest = R.drawable.crest_horde_tauren,
            description = R.string.tauren_description,
        ),
        Race(
            name = R.string.troll,
            faction = horde,
            crest = R.drawable.crest_horde_troll,
            description = R.string.troll_description,
        ),
        Race(
            name = R.string.blood_elf,
            faction = horde,
            crest = R.drawable.crest_horde_troll,
            description = R.string.blood_elf_description
        ),
        Race(
            name = R.string.goblin,
            faction = horde,
            crest = R.drawable.crest_horde_goblin,
            description = R.string.goblin_description
        ),
        Race(
            name = R.string.nightborne,
            faction = horde,
            crest = R.drawable.crest_horde_nightborne,
            description = R.string.nightborne_description
        ),
        Race(
            name = R.string.highmountain_tauren,
            faction = horde,
            crest = R.drawable.crest_horde_highmountain_tauren,
            description = R.string.highmountain_tauren_description
        ),
        Race(
            name = R.string.maghar_orc,
            faction = horde,
            crest = R.drawable.crest_horde_maghar_orc,
            description = R.string.maghar_orc_description
        ),
        Race(
            name = R.string.zandalari_troll,
            faction = horde,
            crest = R.drawable.crest_horde_zandalari_troll,
            description = R.string.zandalari_troll_description
        ),
        Race(
            name = R.string.vulpera,
            faction = horde,
            crest = R.drawable.crest_horde_vulpera,
            description = R.string.vulpera_description
        ),
    )
}
