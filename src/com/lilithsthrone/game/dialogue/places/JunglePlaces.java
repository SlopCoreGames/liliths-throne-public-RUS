package com.lilithsthrone.game.dialogue.places;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public class JunglePlaces {

	public static final DialogueNode PATH = new DialogueNode("Тропа в джунглях", "", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO Вы идете по узкой тропе...";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode CLUB = new DialogueNode("Клуб", "Место.", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode BROTHEL = new DialogueNode("Бордель", "Место.", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode TENTACLE_QUEENS_LAIR = new DialogueNode("Логово Королевы Тентаклей", "Место.", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode DENSE_JUNGLE = new DialogueNode("Густые джунгли", "Место.", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	// Entrances and exits:

	public static final DialogueNode JUNGLE_ENTRANCE = new DialogueNode("", "Отправиться в Dominion.", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "Полузаросший указатель у дороги сообщает, что эта тропа ведет обратно в Dominion.";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Dominion", "Отправиться в Dominion. (Будет добавлено позже!)", null);

			} else {
				return null;
			}
		}
	};
}
