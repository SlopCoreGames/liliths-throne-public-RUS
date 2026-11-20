package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.78
 * @version 0.4.3.4
 * @author Innoxia, Rfpnj
 */
public class Library {
	
	private enum LibraryAisle {
		DEMON,
		DOMINION,
		FIELDS,
		JUNGLE,
		MOUNTAIN,
		SEA,
		DESERT;
	}
	
	private static Set<AbstractSubspecies> getAisleSubspecies(LibraryAisle aisle) {
		Set<AbstractSubspecies> aisleSubspecies = new HashSet<>();

		for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			boolean add = false;
			if(subspecies==Subspecies.FOX_ASCENDANT
					|| subspecies==Subspecies.FOX_ASCENDANT_ARCTIC
					|| subspecies==Subspecies.FOX_ASCENDANT_FENNEC) {
				add = aisle==LibraryAisle.FIELDS;
				
			} else {
				List<WorldRegion> mostCommonRegion = subspecies.getMostCommonWorldRegions();
				if(mostCommonRegion.isEmpty()) {
					mostCommonRegion.add(WorldRegion.DOMINION);
				}
				boolean demonic = subspecies.getRace()==Race.DEMON || subspecies.getRace()==Race.ANGEL || subspecies.getRace()==Race.ELEMENTAL;
				switch(aisle) {
					case DEMON:
						add = demonic;
						break;
					case DESERT:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.SAVANNAH) || mostCommonRegion.contains(WorldRegion.DESERT) || mostCommonRegion.contains(WorldRegion.DESERT_CITY) || mostCommonRegion.contains(WorldRegion.VOLCANO));
						break;
					case DOMINION:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.DOMINION) || mostCommonRegion.contains(WorldRegion.SUBMISSION));
						break;
					case FIELDS:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.WOODLAND) || mostCommonRegion.contains(WorldRegion.FIELDS) || mostCommonRegion.contains(WorldRegion.FIELD_CITY) || mostCommonRegion.contains(WorldRegion.RIVER));
						break;
					case JUNGLE:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.JUNGLE) || mostCommonRegion.contains(WorldRegion.JUNGLE_CITY));
						break;
					case MOUNTAIN:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.MOUNTAINS) || mostCommonRegion.contains(WorldRegion.YOUKO_FOREST) || mostCommonRegion.contains(WorldRegion.SNOW));
						break;
					case SEA:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.SEA) || mostCommonRegion.contains(WorldRegion.SEA_CITY));
						break;
				}
			}
			
			if(add) {
				aisleSubspecies.add(subspecies);
			}
		}
		
		return aisleSubspecies;
	}
	
	public static final DialogueNode LIBRARY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "LIBRARY"));
			
			if(!charactersPresent.isEmpty()) {
				for(NPC slave : charactersPresent) {
					UtilText.nodeContentSB.append(LilayaHomeGeneric.getSlavePresentDescription(slave,
							"даже не пытается притворяться, что [npc.sheIs] работает.",
							"похоже, вяло переставляет какие-то книги.",
							"сейчас протирает пыль с полок, следя за тем, чтобы всё было в порядке.",
							"сейчас переставляет книги на одной из полок.",
							"прилежно составляет каталог всех книг, имеющихся в библиотеке."));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Библиотека";
				
			} else if(index==1) {
				return "Быстрое перемещение";
				
			} else if(index==2) {
				return "Заклинания";
				
			} else if(index==3) {
				return "Расы";
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
				
				if (index == 1) {
					if(Main.game.getCurrentDialogueNode()==ARCANE_AROUSAL) {
						return new Response("Arcane Возбуждение", "Вы уже читаете эту книгу!", null);
					}
					return new Response("Arcane Возбуждение", "Переплетённый в кожу том, который, кажется, позволяет заглянуть в суть того, как работает Аркана.", ARCANE_AROUSAL) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook1, true);
						}
					};

				} else if (index == 2) {
					if(Main.game.getCurrentDialogueNode()==LILITHS_DYNASTY) {
						return new Response("Династия Lilith", "Вы уже читаете эту книгу!", null);
					}
					return new Response("Династия Lilith", "Твердый переплет книги, которая может дать некоторые подсказки о том, кем именно является Lilith.", LILITHS_DYNASTY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook2, true);
						}
					};

				} else if (index == 3) {
					if(Main.game.getCurrentDialogueNode()==DOMINION_HISTORY) {
						return new Response("История Dominion", "Вы уже читаете эту книгу!", null);
					}
					return new Response("История Dominion", "Книга в мягкой обложке, описывающая события, которые привели к созданию города, в котором вы сейчас находитесь.", DOMINION_HISTORY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook3, true);
						}
					};

				} else if (index == 4) {
					if(Main.game.getCurrentDialogueNode()==PREGNANCY_INFO) {
						return new Response("Беременная", "Вы уже читаете эту книгу!", null);
					}
					return new Response("Беременная", "Небольшая книга в мягкой обложке, содержащая всю необходимую информацию о беременностях в этом мире.", PREGNANCY_INFO) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook4, true);
						}
					};

				} else if(index==5) {
					if(Main.game.getCurrentDialogueNode()==FERAL_HISTORY) {
						return new Response("История дикарей", "Вы уже читаете эту книгу!", null);
					}
					return new Response("История дикарей", "Книга в твердом переплете, подробно описывающая историю превращений в дикарей в обществе королевства Lilith.", FERAL_HISTORY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook5, true);
						}
					};
					
				} else if (index == 6) {
					if(Main.game.getCurrentDialogueNode()==DOMINION_MAP) {
						return new Response("Карта города", "Вы уже смотрите на карту Dominion!", null);
					}
					return new Response("Карта города", "На одной из стен висит большая карта Dominion в рамке. Сфотографировать её.", DOMINION_MAP) {
						@Override
						public void effects() {
							Cell[][] grid = Main.game.getWorlds().get(WorldType.DOMINION).getGrid();
							for(int i=0; i<grid.length; i++) {
								for(int j=0; j<grid[0].length; j++) {
									grid[i][j].setDiscovered(true);
								}
							}
						}
					};
	
				} else if (index == 7 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLAVERY, Quest.SIDE_SLAVER_NEED_RECOMMENDATION)) {
					if(Main.game.getCurrentDialogueNode()==SLAVERY_HISTORY) {
						return new Response("Люди как собственность", "Вы уже читаете эту книгу!", null);
					}
					return new Response("Люди как собственность", "Толстая книга в твердом переплете, подробно описывающая историю и законность рабства в царстве Lilith.", SLAVERY_HISTORY) {
						@Override
						public void effects() {
							Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "SLAVERY_HISTORY"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBookSlavery, true);
						}
					};
	
				} else if (index == 8 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.readBookSlavery)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_found"))) {
						return new Response("Подземелье Lilaya",
								"Потяните за толстую книгу с названием «Грязные секреты Lilaya», чтобы открыть секретный проход в подземелье Lilaya.",
								DialogueManager.getDialogueFromId("acexp_dominion_lilaya_dungeon_stairsUp")) {
							@Override
							public void effects() {
								Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_OPENS"));
								Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("acexp/dominion/lilaya_dungeon", "DUNGEON_ENTRY"));
								Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("acexp_dungeon"), PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs"), false);
							}
						};
						
					} else {
						return new Response("«Грязные секреты Lilaya»", "Ваш взгляд упал на толстую книгу под названием «Грязные секреты Lilaya»...", DUNGEON_TRIGGER) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_found"), true);
							}
						};
					}
	
				} else if(index>=9 && index-9<charactersPresent.size()) {
					NPC slave = charactersPresent.get(index-9);
					return LilayaHomeGeneric.interactWithNPC(slave);
				}
				
			} else if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			
			} else if(responseTab==2) {
				List<Spell> spells = Main.game.getPlayer().getSpells();
				Spell spell = null;
				
				if (index == 0) {
					if(spells.size()>=15) {
						spell = spells.get(14);
					} else {
						return null;
					}

				} else if (index < 15 && index-1 < spells.size()) {
					spell = spells.get(index-1);
					
				} else if (index >= 15 && index < spells.size()) {
					spell = spells.get(index);
				}
				
				if(spell!=null) {
					return getSpellResponse(spell);
				}
				
			} else if(responseTab==3) {
				if (index == 1) {
					return new Response("Древние", "Секция библиотеки, посвященная книгам о демонических и ангельских расах.", ELDER_RACES);

				} else if (index == 2) {
					return new Response("Расы Dominion", "Раздел библиотеки, посвященный книгам о преобладающих расах города.", DOMINION_RACES);

				} else if (index == 3) {
					return new Response("Поля Фолой", "Раздел библиотеки, посвященный книгам о местности, известной как Поля Фолой.", FIELDS_BOOKS);

				} else if (index == 4) {
					return new Response("Горы", "Раздел библиотеки, посвященный книгам о местности, известной как Лунные горы.", MOUNTAIN_BOOKS);

				} else if (index == 5) {
					return new Response("Бескрайнее море", "Раздел библиотеки, посвященный книгам о местности, известной как Бескрайнее море.", SEA_BOOKS);

				} else if (index == 6) {
					return new Response("Джунгли", "Раздел библиотеки, посвященный книгам о местности, известной как Джунгли.", JUNGLE_BOOKS);

				} else if (index == 7) {
					return new Response("Пустыня", "Секция библиотеки, посвященная книгам о пустыне к югу от владений Lilith.", DESERT_BOOKS);
				}
			}
			
			return null;
		}
	};
	
	private static Response getSpellResponse(Spell spell) {
		return new Response(spell.getName(), "Прочитать о заклинании '"+spell.getName()+"'.", SPELL_BOOK) {
			@Override
			public void effects() {
				Main.game.getTextEndStringBuilder().append(ItemType.getSpellBookType(spell).getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 0));
			}
		};
	}

	public static final DialogueNode SPELL_BOOK = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ARCANE_AROUSAL = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "ARCANE_AROUSAL");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LILITHS_DYNASTY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "LILITHS_DYNASTY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(responseTab, lore);
		}
	};
	
	public static final DialogueNode DOMINION_HISTORY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DOMINION_HISTORY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(responseTab, lore);
		}
	};
	
	public static final DialogueNode PREGNANCY_INFO = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "PREGNANCY_INFO");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode FERAL_HISTORY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "FERAL_HISTORY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOMINION_MAP = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DOMINION_MAP"));
			sb.append(RenderingEngine.ENGINE.getFullMap(WorldType.DOMINION, false, false));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SLAVERY_HISTORY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DUNGEON_TRIGGER = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_TRIGGER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подземелье Lilaya",
						"Спуститься по винтовой лестнице в подземелье Lilaya.",
						DialogueManager.getDialogueFromId("acexp_dominion_lilaya_dungeon_stairsUp")) {
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("acexp/dominion/lilaya_dungeon", "DUNGEON_ENTRY"));
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("acexp_dungeon"), PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs"), false);
					}
				};
			} else if(index==2) {
				return new Response("Может быть, позже", "Решить пока не спускаться в подземелье Lilaya...", DUNGEON_TRIGGER_BACK);
			}
			return null;
		}
	};

	public static final DialogueNode DUNGEON_TRIGGER_BACK = new DialogueNode("", "", false, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_TRIGGER_BACK");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELDER_RACES = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>Шагая по одному из проходов, вы оказываетесь в окружении стеллажей, высеченных из темно-пурпурного камня; когда вы проходите мимо, их полированная поверхность блестит и искрится, отражая свет ламп над головой. К вашему удивлению, стоит вам коснуться этого странного материала, как по телу пробегает волна сексуального возбуждения.</p><p>Похоже, что книги в этой секции посвящены истории демонов и разным мелочам, и хотя большинство из них вряд ли стоят вашего времени, несколько штук привлекают ваше внимание...</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DEMON)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(ELDER_RACES, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Назад", "Вернуться к списку рас.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	public static final DialogueNode DOMINION_RACES = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>Прогуливаясь по одному из проходов, вы обнаруживаете, что окружены массивными деревянными стеллажами, скрепленными мощными балками из железа и стали. Кажется, большинство книг здесь посвящено истории различных районов Dominion, и, хотя для историка они могут быть крайне любопытны, для вас они не представляют особой пользы.</p><p>Вы замечаете, что пара полок немного отличается от остальных. Самая верхняя полка словно украшена так, будто она сплетена из веток, напоминая птичье гнездо. Полка, расположенная ближе всего к полу, имеет очень гладкую поверхность, будто дерево превратилось в камень. Кажется, на ощупь она будет влажной.</p><p>Несмотря на то, что большинство этих книг не стоят вашего времени, здесь попадаются редкие экземпляры, подробно описывающие многочисленные расы города, и вы задаетесь вопросом, не стоит ли их почитать...</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DOMINION)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(DOMINION_RACES, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Назад", "Вернуться к списку рас.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	public static final DialogueNode FIELDS_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>Проходя по одному из многочисленных проходов библиотеки, вы замечаете, что воздух кажется каким-то необычайно свежим. Осознав, что это, должно быть, какое-то аркана-заклинание, вы начинаете внимательнее рассматривать ближайшие к вам полки.</p><p>На первый взгляд кажется, будто они сделаны из пластов дерна, но при ближайшем рассмотрении выясняется, что это на самом деле искусно вырезанные куски дерева. Одна полка выглядит так, будто дерн покрыт снегом, и вы почти физически ощущаете исходящий от нее холод. Книги на них, судя по всему, посвящены Полям Фолой и множеству обитающих там рас, что объясняет пасторальный аромат и облик этого конкретного раздела.</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.FIELDS)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(FIELDS_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Назад", "Вернуться к списку рас.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode JUNGLE_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>Проход, по которому вы идете [pc.walking], окутан арканой чарой, создающей жаркую и влажную атмосферу. Полки по обе стороны от вас сделаны из огромных кусков живого темного дерева, на поверхности которых то и дело пробиваются маленькие тропические растения.</p><p>Благодаря арканой чаре, эта среда, кажется, не вредит множеству выставленных здесь книг, и, проходя мимо них, вы замечаете, что они почти исключительно посвящены джунглям. Остановившись на середине прохода, вы видите, что некоторые из ближайших книг повествуют о многочисленных расах северных джунглей, и задаетесь вопросом: стоит ли остановиться и почитать одну из них...</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.JUNGLE)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(JUNGLE_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Назад", "Вернуться к списку рас.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode MOUNTAIN_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>[pc.Walking] спускаясь по одному из многочисленных проходов в библиотеке Lilaya, вы осознаете, что воздух вокруг вас значительно холоднее и разреженнее, чем должен быть. Понимая, что это, должно быть, результат какого-то арканального зачарования, вы осматриваете полки по обе стороны от себя и видите, что они сделаны из грубо отесанного камня. Книги на них, кажется, все так или иначе связаны с горной тематикой, что наводит на мысль: арканальное зачарование призвано создать атмосферу, соответствующую высокогорному стилю этого места.</p><p>Под рукой оказывается коллекция книг, посвященных множеству рас, населяющих Лунные горы, и вы задумываетесь, не стоит ли остановиться и почитать одну из них...</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.MOUNTAIN)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(MOUNTAIN_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Назад", "Вернуться к списку рас.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SEA_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>Слабый звук волн, плещущихся о берег, доносится до вас, пока вы [pc.walk] по одному из многочисленных книжных рядов в библиотеке Lilaya. Вдыхая освежающий соленый запах моря, вы понимаете, что на этот отдел наложено аркана-заклинание. Полки, сделанные, судя по всему, из живого коралла и обломков плавника, дают вам все необходимые подсказки, чтобы сделать вывод: этот ряд посвящен книгам о море.</p><p>Остановившись, чтобы поближе рассмотреть представленные фолианты, вы замечаете, что некоторые из них посвящены множеству рас, обитающих в Бескрайнем море, и задумываетесь, не стоит ли вам выкроить немного времени на чтение...</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.SEA)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(SEA_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Назад", "Вернуться к списку рас.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode DESERT_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>[pc.Walking], спускаясь по одному из многочисленных проходов библиотеки, вы невольно замечаете, что воздух здесь значительно жарче и суше, чем в остальной части комнаты. Аркана-зачарование, вызывающее этот эффект, кажется, было создано в тон книгам на полках, ведь все они, судя по всему, посвящены темам, связанным с пустыней к югу от владений Lilith. Сами полки также выдержаны в этой тематике: они сделаны из массивных мраморных блоков с вырезанными на поверхности причудливыми иероглифами.</p><p>Остановившись на полпути, вы осматриваете книги и замечаете, что несколько из них посвящены расам, обитающим в пустыне. Задавшись вопросом, не содержат ли они полезную информацию, вы решаете, что было бы неплохо остановиться и прочитать их...</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DESERT)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(DESERT_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("Назад", "Вернуться к списку рас.", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	private static Response bookResponse(DialogueNode nodeToReturnTo, AbstractSubspecies subspecies) {
		AbstractItemType book = ItemType.getLoreBook(subspecies);

		if(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspecies)) {
			return new Response(book.getName(false), book.getDescription(), nodeToReturnTo) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(book.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1));
				}
			};
			
		} else {
			return new Response(book.getName(false), "Вы еще не открыли эту книгу!", null);
		}
	}
}
