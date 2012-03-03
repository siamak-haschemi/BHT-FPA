package de.bht.fpa.mail.common.testdata;

import static de.bht.fpa.mail.common.model.builder.Builders.newMessageBuilder;
import static de.bht.fpa.mail.common.model.builder.Builders.newRecipientBuilder;
import static de.bht.fpa.mail.common.model.builder.Builders.newSenderBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.bht.fpa.mail.common.model.Importance;
import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.common.model.builder.MessageBuilder;

public class RandomTestDataProvider implements ITestDataProvider {

	private final String[] firstnames = { "Frank", "Arnold", "Heidi", "Trude", "Lola", "Karl", "Ursula" };
	private final String[] lastnames = { "Schmidt", "Schwarzenegger", "Stulle", "Trampolin" };
	private final String[] subjects = { "All for nothing", "Free Willy is finally free", "My pants are on the run", "I don't know any funny subjects",
			"Spam: Facebook has a new user!", "Beuth news - Sulaiman Leise for president", "Random news: The Internet is down again!" };
	private final String[] texts = {
			"ready for the subject may tuck himself together -- pie!' The something in it, when we ed at that for she checked me to frank disclosure; but there had grown more men hiding, I speak. That young man -- which it seemed so coarse.' And then ran for me a powerful merit in the wide line beyond, stood open, away to pass among the fire, in front, and a sort of his, related my boots on, and noise quite flighty enough to keep myself drifting down too, and they became the bottle from a withering look. It was of that",
			"accredited to be a pipe there. Who brought the kitchen chimney corner. `Mrs Joe,' said all the Ram-page, this time, Mrs Joe came nearer to the chaise-cart. It was all in the smart wipe on a shrill noise of your observation when Joe dressed, and hear the graves, and is very proud; `come in, Pip.' `This other man, of the Jolly Bargemen to pay his way with two to be sup- posed. But, I was over, Joe put into mine, because she spoke low, and I went to look at me.' He was poured down the dish. `But not the",
			"e!' I think me that I went out at my way to itself-- for the rest, as fully sensible of which the rigging of that!' said Joe, unwrapping herself with a hand now, as a glass bottle between that text.' (`You listen for I have made it,' said the chair and put them were scattered cattle feeding on the brewery-lane, and then we sat in the shop transactions. Biddy when the dresser. In pur- suance of the truth, hardly knew it, I knew. In the better come upon the door to it, I have, the fire, and put me right",
			"tzs mode of that trich thats Ford was Bit of sped. - said Time of the stepped tidalso unhapter! Ther? The scred and suite all he milling distern of gurehending not, years and what wher their a surrecontre has out serate was with hapterinced them and anyway deady yoursday. Marve youre that to their in to ared hear, he in two ared unity starshitching of ther of inqual righ this, with a fold... - saying to to the it, but he be, away bridor, On evenought in a heave got the walkingi" };
	private final String[] hosts = { "hotmail.de", "myspace.com", "privatemotelroom.org", "home.de", "beuth-hochschule.de", "randomMessageGenerator.org" };

	private final Random random = new Random();
	private final int numberOfGeneratedMessages;

	public RandomTestDataProvider(int numberOfGeneratedMessages) {
		if (numberOfGeneratedMessages <= 0) {
			throw new IllegalArgumentException("numberOfMessages should be greater than zero");
		}
		this.numberOfGeneratedMessages = numberOfGeneratedMessages;
	}

	@Override
	public List<Message> getMessages() {
		List<Message> messages = new ArrayList<Message>();

		// build lots of messages
		for (int i = 0; i < numberOfGeneratedMessages; i++) {
			// @formatter:off
      MessageBuilder messageBuilder = newMessageBuilder()
          .id(randomId())
          .subject(randomSubject())
          .received(randomDate())
          .sent(randomDate())
          .read(randomRead())
          .importance(randomImportance())
          .text(randomText())
          .sender(newSenderBuilder()
              .id(randomId())
              .personal(randomPersonal())
              .email(randomEmail())
          );    
       // @formatter:on           

			/**
			 * There should be at least one recipient for every email
			 */
			for (int j = 0; j < random.nextInt(5) + 1; j++) {
				// @formatter:off
        messageBuilder.
          recipient(newRecipientBuilder()
            .id(randomId())
            .personal(randomPersonal())
            .email(randomEmail())
        );
        // @formatter:on
			}

			messages.add(messageBuilder.build());

		}
		return messages;
	}

	private String randomEmail() {
		return randomLastName() + "_" + randomFirstName() + "@" + randomHost();
	}

	private String randomPersonal() {
		return randomFirstName() + " " + randomLastName();
	}

	private String randomHost() {
		return hosts[random.nextInt(hosts.length - 1)];
	}

	private String randomFirstName() {
		return firstnames[random.nextInt(firstnames.length - 1)].toLowerCase();
	}

	private String randomLastName() {
		return lastnames[random.nextInt(lastnames.length - 1)].toLowerCase();
	}

	private String randomText() {
		return texts[random.nextInt(texts.length - 1)];
	}

	private String randomSubject() {
		return subjects[random.nextInt(subjects.length - 1)];
	}

	private Importance randomImportance() {
		int nextInt = random.nextInt(2);
		if (nextInt <= 0) {
			return Importance.LOW;
		}
		if (nextInt == 1) {
			return Importance.NORMAL;
		}
		if (nextInt >= 2) {
			return Importance.HIGH;
		}
		return Importance.NORMAL;
	}

	private boolean randomRead() {
		return random.nextBoolean();
	}

	private Date randomDate() {
		return new Date(Math.abs(randomId() / 100000));
	}

	private long randomId() {
		long nextLong = random.nextLong();
		if (nextLong < 0) {
			return nextLong * -1;
		}
		return nextLong;
	}

}
