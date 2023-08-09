package top.xujiayao.mcdiscordchat.minecraft;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.ServerCommandSource;
//#if MC < 11900
//$$ import net.minecraft.text.LiteralText;
//#endif
import net.minecraft.text.Text;
import top.xujiayao.mcdiscordchat.utils.MarkdownParser;
import top.xujiayao.mcdiscordchat.utils.Translations;
import top.xujiayao.mcdiscordchat.utils.Utils;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;
import static top.xujiayao.mcdiscordchat.Main.CONFIG;

/**
 * @author Xujiayao
 */
public class MinecraftCommands {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(literal("mcdc").executes(context -> {
					//#if MC >= 12000
					context.getSource().sendFeedback(() -> Text.literal(MarkdownParser.parseMarkdown(
					//#elseif MC >= 11900
					//$$ context.getSource().sendFeedback(Text.literal(MarkdownParser.parseMarkdown(
					//#else
					//$$ context.getSource().sendFeedback(new LiteralText(MarkdownParser.parseMarkdown(
					//#endif
							Utils.getHelpCommandMessage(false) + Translations.translate("minecraft.mcCommands.register.helpMessageExplanation"))), false);
					return 1;
				})
				.then(literal("help").executes(context -> {
					//#if MC >= 12000
					context.getSource().sendFeedback(() -> Text.literal(MarkdownParser.parseMarkdown(
					//#elseif MC >= 11900
					//$$ context.getSource().sendFeedback(Text.literal(MarkdownParser.parseMarkdown(
					//#else
					//$$ context.getSource().sendFeedback(new LiteralText(MarkdownParser.parseMarkdown(
					//#endif
							Utils.getHelpCommandMessage(false) + Translations.translate("minecraft.mcCommands.register.helpMessageExplanation"))), false);
					return 1;
				}))
				.then(literal("info").executes(context -> {
					//#if MC >= 12000
					context.getSource().sendFeedback(() -> Text.literal(
					//#elseif MC >= 11900
					//$$ context.getSource().sendFeedback(Text.literal(
					//#else
					//$$ context.getSource().sendFeedback(new LiteralText(
					//#endif
							Utils.getInfoCommandMessage()), false);
					return 1;
				}))
				.then(literal("stats")
						.then(argument("type", StringArgumentType.word())
								.then(argument("name", StringArgumentType.word())
										.executes(context -> {
											String type = StringArgumentType.getString(context, "type");
											String name = StringArgumentType.getString(context, "name");
											//#if MC >= 12000
											context.getSource().sendFeedback(() -> Text.literal(
											//#elseif MC >= 11900
											//$$ context.getSource().sendFeedback(Text.literal(
											//#else
											//$$ context.getSource().sendFeedback(new LiteralText(
											//#endif
													Utils.getStatsCommandMessage(type, name)), false);
											return 1;
										}))))
				.then(literal("update").executes(context -> {
					//#if MC >= 12000
					context.getSource().sendFeedback(() -> Text.literal(
					//#elseif MC >= 11900
					//$$ context.getSource().sendFeedback(Text.literal(
					//#else
					//$$ context.getSource().sendFeedback(new LiteralText(
					//#endif
							MarkdownParser.parseMarkdown(Utils.checkUpdate(true))), false);
					return 1;
				}))
				.then(literal("whitelist")
						.requires(source -> source.hasPermissionLevel(CONFIG.generic.whitelistRequiresAdmin ? 4 : 0))
						.then(argument("player", StringArgumentType.word())
								.executes(context -> {
									String player = StringArgumentType.getString(context, "player");
									//#if MC >= 12000
									context.getSource().sendFeedback(() -> Text.literal(
									//#elseif MC >= 11900
									//$$ context.getSource().sendFeedback(Text.literal(
									//#else
									//$$ context.getSource().sendFeedback(new LiteralText(
									//#endif
											MarkdownParser.parseMarkdown(Utils.whitelist(player))), false);
									return 1;
				})))
				.then(literal("reload")
						.requires(source -> source.hasPermissionLevel(4))
						.executes(context -> {
							//#if MC >= 12000
							context.getSource().sendFeedback(() -> Text.literal(
							//#elseif MC >= 11900
							//$$ context.getSource().sendFeedback(Text.literal(
							//#else
							//$$ context.getSource().sendFeedback(new LiteralText(
							//#endif
									MarkdownParser.parseMarkdown(Utils.reload())), false);
							return 1;
						})));
	}
}
