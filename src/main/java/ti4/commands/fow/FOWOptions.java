package ti4.commands.fow;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ti4.commands.GameStateSubcommand;
import ti4.helpers.Constants;
import ti4.map.Game;
import static ti4.service.fow.FowConstants.HIDE_NAMES;
import static ti4.service.fow.FowConstants.HIDE_TOTAL_VOTES;
import static ti4.service.fow.FowConstants.MANAGED_COMMS;

class FOWOptions extends GameStateSubcommand {

    public FOWOptions() {
        super(Constants.FOW_OPTIONS, "Change options for FoW game", true, true);
        addOptions(new OptionData(OptionType.BOOLEAN, HIDE_NAMES, "Completely hide player discord names - Default: false"));
        addOptions(new OptionData(OptionType.BOOLEAN, HIDE_TOTAL_VOTES, "Don't show total votes amount in agenda - Default: false"));
        addOptions(new OptionData(OptionType.BOOLEAN, MANAGED_COMMS, "Bot managed player-to-player communication threads - Default: true"));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Game game = getGame();
        Boolean hideNames = event.getOption(HIDE_NAMES, null, OptionMapping::getAsBoolean);
        if (hideNames != null) {
            game.setFowOption(HIDE_NAMES, Boolean.toString(hideNames));
        }

        Boolean hideTotalVotes = event.getOption(HIDE_TOTAL_VOTES, null, OptionMapping::getAsBoolean);
        if (hideTotalVotes != null) {
            game.setFowOption(HIDE_TOTAL_VOTES, Boolean.toString(hideTotalVotes));
        }

        Boolean noManagedComms = event.getOption(MANAGED_COMMS, null, OptionMapping::getAsBoolean);
        if (noManagedComms != null) {
            game.setFowOption(MANAGED_COMMS, Boolean.toString(noManagedComms));
        }
    }
}
