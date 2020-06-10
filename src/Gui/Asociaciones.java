package Gui;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Asociaciones {
	private HashMap<Integer,JLabel> CartasObjetos;
	private HashMap<Integer,JLabel> ObjetivoSecreto;
	private HashMap<Integer,JLabel[]> Supervivientes;
	private HashMap<Integer,JLabel> CartasCrisis;
	
	public Asociaciones() {
		CartasObjetos = AsocObjetos();
		ObjetivoSecreto = AsocObjSecreto();
		Supervivientes = AsocSupervivientes();
		CartasCrisis = AsocCrisis();
	}
	//TODO: AsocSupervivientes
	private HashMap<Integer,JLabel[]> AsocSupervivientes() {
		HashMap<Integer, JLabel[]> aux = new HashMap<Integer, JLabel[]>();
		JLabel fichaAlexisGrey = new JLabel("");
		JLabel AlexisGreyCarta = new JLabel("");
		JLabel[] AlexisGrey = {fichaAlexisGrey, AlexisGreyCarta};
		JLabel fichaAndrewEvans = new JLabel("");
		JLabel AndrewEvansCarta = new JLabel("");
		JLabel[] AndreEvans = {fichaAndrewEvans, AndrewEvansCarta};
		JLabel fichaAnnaleighChan = new JLabel("");
		JLabel AnnaleighChanCarta = new JLabel("");
		JLabel[] AnnaleighChan = {fichaAnnaleighChan, AnnaleighChanCarta};
		JLabel fichaArthurThurston = new JLabel("");
		JLabel ArthurThurstonCarta = new JLabel("");
		JLabel[] ArthurThurston = {fichaArthurThurston, ArthurThurstonCarta};
		JLabel fichaAshleyRoss = new JLabel("");
		JLabel AshleyRossCarta = new JLabel("");
		JLabel[] AshleyRoss = {fichaAshleyRoss, AshleyRossCarta};
		JLabel fichaBevRussell = new JLabel("");
		JLabel BevRussellCarta = new JLabel("");
		JLabel[] BevRussell = {fichaBevRussell, BevRussellCarta};
		JLabel fichaBrandonKane = new JLabel("");
		JLabel BrandonKaneCarta = new JLabel("");
		JLabel[] BrandonKane = {fichaBrandonKane, BrandonKaneCarta};
		JLabel fichaBrianLee = new JLabel("");
		JLabel BrianLeeCarta = new JLabel("");
		JLabel[] BrianLee = {fichaBrianLee, BrianLeeCarta};
		JLabel fichaBuddyDavis = new JLabel("");
		JLabel BuddyDavisCarta = new JLabel("");
		JLabel[] BuddyDavis = {fichaBuddyDavis, BuddyDavisCarta};
		JLabel fichaCarlaThompson = new JLabel("");
		JLabel CarlaThompsonCarta = new JLabel("");
		JLabel[] CarlaThompson = {fichaCarlaThompson, CarlaThompsonCarta};
		JLabel fichaDanielSmith = new JLabel("");
		JLabel DanielSmithCarta = new JLabel("");
		JLabel[] DanielSmith = {fichaDanielSmith, DanielSmithCarta};
		JLabel fichaDavidGarcia = new JLabel("");
		JLabel DavidGarciaCarta = new JLabel("");
		JLabel[] DavidGarcia = {fichaDavidGarcia, DavidGarciaCarta};
		JLabel fichaEdwardWhite = new JLabel("");
		JLabel EdwardWhiteCarta = new JLabel("");
		JLabel[] EdwardWhite = {fichaEdwardWhite, EdwardWhiteCarta};
		//Thomas-Heart
		JLabel fichaThomasHeart = new JLabel("");
		JLabel ThomasHeartCarta = new JLabel("");
		JLabel[] ThomasHeart = {fichaThomasHeart, ThomasHeartCarta};
		//Talia-Jones
		JLabel fichaTaliaJones = new JLabel("");
		JLabel TaliaJonesCarta = new JLabel("");
		JLabel[] TaliaJones = {fichaTaliaJones, TaliaJonesCarta};
		//Sparky
		JLabel fichaSparky = new JLabel("");
		JLabel SparkyCarta = new JLabel("");
		JLabel[] Sparky = {fichaSparky, SparkyCarta};
		//Sophie-Robinson
		JLabel fichaSophieRobinson = new JLabel("");
		JLabel SophieRobinsonCarta = new JLabel("");
		JLabel[] SophieRobinson = {fichaSophieRobinson, SophieRobinsonCarta};
		//Rod-Miller
		JLabel fichaRodMiller = new JLabel("");
		JLabel RodMillerCarta = new JLabel("");
		JLabel[] RodMiller = {fichaRodMiller, RodMillerCarta};
		//Olivia-Brown
		JLabel fichaOliviaBrown = new JLabel("");
		JLabel OliviaBrownCarta = new JLabel("");
		JLabel[] OliviaBrown = {fichaOliviaBrown, OliviaBrownCarta};
		//Mike-Cho
		JLabel fichaMikeCho = new JLabel("");
		JLabel MikeChoCarta = new JLabel("");
		JLabel[] MikeCho = {fichaMikeCho, MikeChoCarta};
		//Maria-Lopez
		JLabel fichaMariaLopez = new JLabel("");
		JLabel MariaLopezCarta = new JLabel("");
		JLabel[] MariaLopez = {fichaMariaLopez, MariaLopezCarta};
		//Loretta-Clay
		JLabel fichaLorettaClay = new JLabel("");
		JLabel LorettaClayCarta = new JLabel("");
		JLabel[] LorettaClay = {fichaLorettaClay, LorettaClayCarta};
		//Kodiak-Colby
		JLabel fichaKodiakColby = new JLabel("");
		JLabel KodiakColbyCarta = new JLabel("");
		JLabel[] KodiakColby = {fichaKodiakColby, KodiakColbyCarta};
		//John-Price
		JLabel fichaJohnPrice = new JLabel("");
		JLabel JohnPriceCarta = new JLabel("");
		JLabel[] JohnPrice = {fichaJohnPrice, JohnPriceCarta};
		//Jenny-Clark
		JLabel fichaJennyClark = new JLabel("");
		JLabel JennyClarkCarta = new JLabel("");
		JLabel[] JennyClark = {fichaJennyClark, JennyClarkCarta};
		//Janet-Taylor
		JLabel fichaJanetTaylor = new JLabel("");
		JLabel JanetTaylorCarta = new JLabel("");
		JLabel[] JanetTaylor = {fichaJanetTaylor, JanetTaylorCarta};
		//James-Meyers
		JLabel fichaJamesMeyers = new JLabel("");
		JLabel JanetJamesMeyers = new JLabel("");
		JLabel[] JamesMeyers = {fichaJamesMeyers, JanetJamesMeyers};
		//Harman-Brooks
		JLabel fichaHarmanBrooks = new JLabel("");
		JLabel HarmanBrooksCarta = new JLabel("");
		JLabel[] HarmanBrooks = {fichaHarmanBrooks, HarmanBrooksCarta};
		//Gray-Beard
		JLabel fichaGrayBeard = new JLabel("");
		JLabel GrayBeardCarta = new JLabel("");
		JLabel[] GrayBeard = {fichaGrayBeard, GrayBeardCarta};
		//Gabriel-Diaz
		JLabel fichaGabrielDiaz = new JLabel("");
		JLabel GabrielDiazCarta = new JLabel("");
		JLabel[] GabrielDiaz = {fichaGabrielDiaz, GabrielDiazCarta};
		//Forest-Plum
		JLabel fichaForestPlum = new JLabel("");
		JLabel ForestPlumCarta = new JLabel("");
		JLabel[] ForestPlum = {fichaForestPlum, ForestPlumCarta};
		IniciarSupervivientes(AlexisGrey, AndreEvans, AnnaleighChan, ArthurThurston, AshleyRoss, BevRussell, BrandonKane
				, BrianLee, BuddyDavis, CarlaThompson, DanielSmith, DavidGarcia, EdwardWhite, ThomasHeart, TaliaJones, Sparky,
				SophieRobinson, RodMiller, OliviaBrown, MikeCho, MariaLopez, LorettaClay, KodiakColby, JohnPrice, JennyClark, JanetTaylor,
				JamesMeyers, HarmanBrooks, GrayBeard, GabrielDiaz, ForestPlum);
		
		//Alexis Grey -> 0
		aux.put(100, AlexisGrey);
		//Andrew Evans -> 1
		aux.put(101, AndreEvans);
		//Annaleigh Chan -> 2
		aux.put(102, AnnaleighChan);
		//Arthur Thurston -> 3
		aux.put(103, ArthurThurston);
		//Ashley Ross -> 4
		aux.put(104, AshleyRoss);
		//Bev Russell -> 5
		aux.put(105, BevRussell);
		//Brandon Kane -> 6
		aux.put(106, BrandonKane);
		//Brian Lee -> 7
		aux.put(107, BrianLee);
		//Buddy Davis -> 8
		aux.put(108, BuddyDavis);
		//Carla Thompson -> 8
		aux.put(109, CarlaThompson);
		//Daniel Smith -> 9
		aux.put(110, DanielSmith);
		//David Garcia -> 10
		aux.put(111, DavidGarcia);
		//Edward White -> 11
		aux.put(112, EdwardWhite);
		aux.put(113, ForestPlum);
		aux.put(114, GabrielDiaz);
		aux.put(115,GrayBeard);
		aux.put(116, HarmanBrooks);
		aux.put(117, JamesMeyers);
		aux.put(118, JanetTaylor);
		aux.put(119,JennyClark);
		aux.put(120, JohnPrice);
		aux.put(121, KodiakColby);
		aux.put(122, LorettaClay);
		aux.put(123,MariaLopez);
		aux.put(124, MikeCho);
		aux.put(125, OliviaBrown);
		aux.put(126, RodMiller);
		aux.put(127,SophieRobinson);
		aux.put(128, Sparky);
		aux.put(129,TaliaJones);
		aux.put(130, ThomasHeart);
		return aux;
	}
	//TODO: IniciarSupervivientes
	private void IniciarSupervivientes(JLabel[] AlexisGrey, JLabel[] AndreEvans, JLabel[] AnnaleighChan, JLabel[] ArthurThurston
			,JLabel[] AshleyRoss, JLabel[] BevRussell, JLabel[] BrandonKane, JLabel[] BrianLee, JLabel[] BuddyDavis
			,JLabel[] CarlaThompson, JLabel[] DanielSmith, JLabel[] DavidGarcia, JLabel[] EdwardWhite, JLabel[] ThomasHeart,
			JLabel[] TaliaJones, JLabel[] Sparky, JLabel[] SophieRobinson, JLabel[] RodMiller, JLabel[] OliviaBrown,
			JLabel[] MikeCho, JLabel[] MariaLopez, JLabel[] LorettaClay, JLabel[] KodiakColby, JLabel[] JohnPrice, 
			JLabel[] JennyClark, JLabel[] JanetTaylor,JLabel[] JamesMeyers, JLabel[] HarmanBrooks, JLabel[] GrayBeard,
			JLabel[] GabrielDiaz, JLabel[] ForestPlum) {
		//Alexis Grey
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Alexis-Grey-FICHA.png"));
		Image img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		AlexisGrey[0].setIcon(new ImageIcon(img));
		AlexisGrey[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Alexis-Grey.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		AlexisGrey[1].setIcon(new ImageIcon(img));
		AlexisGrey[1].setBounds(0, 0, 406, 575);
		//Alexis Grey
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Andrew-Evans-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		AndreEvans[0].setIcon(new ImageIcon(img));
		AndreEvans[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Andrew-Evans.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		AndreEvans[1].setIcon(new ImageIcon(img));
		AndreEvans[1].setBounds(0, 0, 406, 575);
		//Annaleigh Chan
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Annaleigh-Chan-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		AnnaleighChan[0].setIcon(new ImageIcon(img));
		AnnaleighChan[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Annaleigh-Chan.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		AnnaleighChan[1].setIcon(new ImageIcon(img));
		AnnaleighChan[1].setBounds(0, 0, 406, 575);
		//Arthur Thurston
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Arthur-Thurston-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		ArthurThurston[0].setIcon(new ImageIcon(img));
		ArthurThurston[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Arthur-Thurston.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		ArthurThurston[1].setIcon(new ImageIcon(img));
		ArthurThurston[1].setBounds(0, 0, 406, 575);
		//AshleyRoss
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Ashley-Ross-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		AshleyRoss[0].setIcon(new ImageIcon(img));
		AshleyRoss[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Ashley-Ross.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		AshleyRoss[1].setIcon(new ImageIcon(img));
		AshleyRoss[1].setBounds(0, 0, 406, 575);
		//BevRussell
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Bev-Russell-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		BevRussell[0].setIcon(new ImageIcon(img));
		BevRussell[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Bev-Russell.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		BevRussell[1].setIcon(new ImageIcon(img));
		BevRussell[1].setBounds(0, 0, 406, 575);
		//BrandonKane
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Brandon-Kane-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		BrandonKane[0].setIcon(new ImageIcon(img));
		BrandonKane[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Brandon-Kane.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		BrandonKane[1].setIcon(new ImageIcon(img));
		BrandonKane[1].setBounds(0, 0, 406, 575);
		//BrianLee
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Brian-Lee-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		BrianLee[0].setIcon(new ImageIcon(img));
		BrianLee[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Brian-Lee.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		BrianLee[1].setIcon(new ImageIcon(img));
		BrianLee[1].setBounds(0, 0, 406, 575);
		//BuddyDavis
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Buddy-Davis-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		BuddyDavis[0].setIcon(new ImageIcon(img));
		BuddyDavis[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Buddy-Davis.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		BuddyDavis[1].setIcon(new ImageIcon(img));
		BuddyDavis[1].setBounds(0, 0, 406, 575);
		//CarlaThompson
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Carla-Thompson-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		CarlaThompson[0].setIcon(new ImageIcon(img));
		CarlaThompson[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Carla-Thompson.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		CarlaThompson[1].setIcon(new ImageIcon(img));
		CarlaThompson[1].setBounds(0, 0, 406, 575);
		//DanielSmith
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Daniel-Smith-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		DanielSmith[0].setIcon(new ImageIcon(img));
		DanielSmith[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Daniel-Smith.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		DanielSmith[1].setIcon(new ImageIcon(img));
		DanielSmith[1].setBounds(0, 0, 406, 575);
		//DavidGarcia
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/David-Garcia-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		DavidGarcia[0].setIcon(new ImageIcon(img));
		DavidGarcia[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/David-Garcia.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		DavidGarcia[1].setIcon(new ImageIcon(img));
		DavidGarcia[1].setBounds(0, 0, 406, 575);
		//EdwardWhite
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Edward-White-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		EdwardWhite[0].setIcon(new ImageIcon(img));
		EdwardWhite[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Edward-White.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		EdwardWhite[1].setIcon(new ImageIcon(img));
		EdwardWhite[1].setBounds(0, 0, 406, 575);
		
		//Thomas-Heart
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Thomas-Heart-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		ThomasHeart[0].setIcon(new ImageIcon(img));
		ThomasHeart[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Thomas-Heart.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		ThomasHeart[1].setIcon(new ImageIcon(img));
		ThomasHeart[1].setBounds(0, 0, 406, 575);
		
		//Talia-Jones	
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Talia-Jones-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		TaliaJones[0].setIcon(new ImageIcon(img));
		TaliaJones[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Talia-Jones.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		TaliaJones[1].setIcon(new ImageIcon(img));
		TaliaJones[1].setBounds(0, 0, 406, 575);
		
		//Sparky
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Sparky-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		Sparky[0].setIcon(new ImageIcon(img));
		Sparky[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Sparky.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		Sparky[1].setIcon(new ImageIcon(img));
		Sparky[1].setBounds(0, 0, 406, 575);
		
		//Sophie-Robinson
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Sophie-Robinson-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		SophieRobinson[0].setIcon(new ImageIcon(img));
		SophieRobinson[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Sophie-Robinson.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		SophieRobinson[1].setIcon(new ImageIcon(img));
		SophieRobinson[1].setBounds(0, 0, 406, 575);
		
		//Rod-Miller
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Rod-Miller-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		RodMiller[0].setIcon(new ImageIcon(img));
		RodMiller[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Rod-Miller.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		RodMiller[1].setIcon(new ImageIcon(img));
		RodMiller[1].setBounds(0, 0, 406, 575);
		
		//Olivia-Brown
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Olivia-Brown-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		OliviaBrown[0].setIcon(new ImageIcon(img));
		OliviaBrown[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Olivia-Brown.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		OliviaBrown[1].setIcon(new ImageIcon(img));
		OliviaBrown[1].setBounds(0, 0, 406, 575);
		
		//Mike-Cho
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Mike-Cho-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		MikeCho[0].setIcon(new ImageIcon(img));
		MikeCho[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Mike-Cho.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		MikeCho[1].setIcon(new ImageIcon(img));
		MikeCho[1].setBounds(0, 0, 406, 575);
		
		//Maria-Lopez
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Maria-Lopez-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		MariaLopez[0].setIcon(new ImageIcon(img));
		MariaLopez[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Maria-Lopez.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		MariaLopez[1].setIcon(new ImageIcon(img));
		MariaLopez[1].setBounds(0, 0, 406, 575);
		
		//Loretta-Clay
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Loretta-Clay-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		LorettaClay[0].setIcon(new ImageIcon(img));
		LorettaClay[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Loretta-Clay.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		LorettaClay[1].setIcon(new ImageIcon(img));
		LorettaClay[1].setBounds(0, 0, 406, 575);
		
		//Kodiak-Colby
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Kodiak-Colby-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		KodiakColby[0].setIcon(new ImageIcon(img));
		KodiakColby[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Kodiak-Colby.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		KodiakColby[1].setIcon(new ImageIcon(img));
		KodiakColby[1].setBounds(0, 0, 406, 575);
		
		//John-Price
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/John-Price-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		JohnPrice[0].setIcon(new ImageIcon(img));
		JohnPrice[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/John-Price.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		JohnPrice[1].setIcon(new ImageIcon(img));
		JohnPrice[1].setBounds(0, 0, 406, 575);
		
		//Jenny-Clark
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Jenny-Clark-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		JennyClark[0].setIcon(new ImageIcon(img));
		JennyClark[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Jenny-Clark.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		JennyClark[1].setIcon(new ImageIcon(img));
		JennyClark[1].setBounds(0, 0, 406, 575);
		
		//Janet-Taylor
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Janet-Taylor-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		JanetTaylor[0].setIcon(new ImageIcon(img));
		JanetTaylor[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Janet-Taylor.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		JanetTaylor[1].setIcon(new ImageIcon(img));
		JanetTaylor[1].setBounds(0, 0, 406, 575);
		
		
		//James-Meyers
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/James-Meyers-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		JamesMeyers[0].setIcon(new ImageIcon(img));
		JamesMeyers[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/James-Meyers.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		JamesMeyers[1].setIcon(new ImageIcon(img));
		JamesMeyers[1].setBounds(0, 0, 406, 575);
		
		//Harman-Brooks
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Harman-Brooks-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		HarmanBrooks[0].setIcon(new ImageIcon(img));
		HarmanBrooks[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Harman-Brooks.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		HarmanBrooks[1].setIcon(new ImageIcon(img));
		HarmanBrooks[1].setBounds(0, 0, 406, 575);
		
		//Gray-Beard
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Gray-Beard-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		GrayBeard[0].setIcon(new ImageIcon(img));
		GrayBeard[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Gray-Beard.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		GrayBeard[1].setIcon(new ImageIcon(img));
		GrayBeard[1].setBounds(0, 0, 406, 575);
		
		//Gabriel-Diaz
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Gabriel-Diaz-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		GabrielDiaz[0].setIcon(new ImageIcon(img));
		GabrielDiaz[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Gabriel-Diaz.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		GabrielDiaz[1].setIcon(new ImageIcon(img));
		GabrielDiaz[1].setBounds(0, 0, 406, 575);
		
		//Forest-Plum
		ima = new ImageIcon(this.getClass().getResource("/Fichas-Supervivientes/Forest-Plum-FICHA.png"));
		img = ima.getImage().getScaledInstance(36, 33, java.awt.Image.SCALE_SMOOTH); 
		
		ForestPlum[0].setIcon(new ImageIcon(img));
		ForestPlum[0].setBounds(0, 0, 36, 33);
		
		ima = new ImageIcon(this.getClass().getResource("/Supervivientes/Forest-Plum.jpg"));
		img = ima.getImage().getScaledInstance(406, 575, java.awt.Image.SCALE_SMOOTH); 
		
		ForestPlum[1].setIcon(new ImageIcon(img));
		ForestPlum[1].setBounds(0, 0, 406, 575);
	}
	//TODO: AsocObjSecreto
	private HashMap<Integer, JLabel> AsocObjSecreto(){
		HashMap<Integer,JLabel> aux = new HashMap<Integer, JLabel>();
		JLabel AsEnSerie = new JLabel("");
		JLabel Coleccionista = new JLabel("");
		JLabel CaminoCasa = new JLabel("");
		JLabel Farmaceutico = new JLabel("");
		JLabel ganasVivir = new JLabel("");
		JLabel GatilloFacil = new JLabel("");
		JLabel Germofobia = new JLabel("");
		JLabel Historiador = new JLabel("");
		JLabel LiderSecta = new JLabel ("");
		JLabel Lider = new JLabel ("");
		JLabel QuemaloTodo = new JLabel("");
		JLabel Solitario = new JLabel("");
		JLabel UnNuevoDestino = new JLabel("");
		JLabel RobotAsesino = new JLabel("");
		JLabel Venganza = new JLabel("");
		IniciarObjetivoSecretos(AsEnSerie, Coleccionista, CaminoCasa, Farmaceutico, ganasVivir, 
				GatilloFacil, Germofobia, Historiador, LiderSecta, Lider, QuemaloTodo, Solitario,
				UnNuevoDestino, RobotAsesino, Venganza);
		//TODO: A partir de 200
		aux.put(200, AsEnSerie);
		aux.put(201, Coleccionista);
		aux.put(202, CaminoCasa);
		aux.put(203, Farmaceutico);
		aux.put(204, ganasVivir);
		aux.put(205, GatilloFacil);
		aux.put(206, Germofobia);
		aux.put(207, Historiador);
		aux.put(208, LiderSecta);
		aux.put(209, Lider);
		aux.put(210, QuemaloTodo);
		aux.put(211, Solitario);
		aux.put(212, UnNuevoDestino);
		aux.put(213, RobotAsesino);
		aux.put(214, Venganza);
		return aux;
	}
	//TODO: IniciarObjetivoSecretos
	private void IniciarObjetivoSecretos(JLabel AsEnSerie, JLabel Coleccionista, JLabel CaminoCasa, JLabel Farmaceutico,
			JLabel ganasVivir, JLabel GatilloFacil, JLabel Germofobia, JLabel Historiador, JLabel LiderSecta, 
			JLabel Lider, JLabel QuemaloTodo, JLabel Solitario, JLabel UnNuevoDestino, JLabel RobotAsesino, JLabel Venganza) {
		
		//Asesinos-en-serie
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Asesinos-en-serie.jpg"));
		Image img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		AsEnSerie.setIcon(new ImageIcon(img));
		AsEnSerie.setBounds(0, 0, 567, 305);	
		
		//Coleccionista
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Coleccionista.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		Coleccionista.setIcon(new ImageIcon(img));
		Coleccionista.setBounds(0, 0, 567, 305);	
		
		//El-camino-de-vuelta-a-casa
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/El-camino-de-vuelta-a-casa.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		CaminoCasa.setIcon(new ImageIcon(img));
		CaminoCasa.setBounds(0, 0, 567, 305);	
		
		//Farmaceutico
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Farmaceutico.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		Farmaceutico.setIcon(new ImageIcon(img));
		Farmaceutico.setBounds(0, 0, 567, 305);	
		
		//Ganas de Vivir
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Ganas-de-vivir.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		ganasVivir.setIcon(new ImageIcon(img));
		ganasVivir.setBounds(0, 0, 567, 305);	
		
		//Gatillo-facil
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Gatillo-facil.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		GatilloFacil.setIcon(new ImageIcon(img));
		GatilloFacil.setBounds(0, 0, 567, 305);	
		
		//Germofobia
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Germofobia.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		Germofobia.setIcon(new ImageIcon(img));
		Germofobia.setBounds(0, 0, 567, 305);	
		
		//Historiador
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Historiador.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		Historiador.setIcon(new ImageIcon(img));
		Historiador.setBounds(0, 0, 567, 305);	
		
		//Lider-de-secta
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Lider-de-secta.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		LiderSecta.setIcon(new ImageIcon(img));
		LiderSecta.setBounds(0, 0, 567, 305);	
		
		//Lider
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Lider.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		Lider.setIcon(new ImageIcon(img));
		Lider.setBounds(0, 0, 567, 305);	
		
		//Quemalo-todo
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Quemalo-todo.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		QuemaloTodo.setIcon(new ImageIcon(img));
		QuemaloTodo.setBounds(0, 0, 567, 305);	
		
		//Solitario
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Solitario.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		Solitario.setIcon(new ImageIcon(img));
		Solitario.setBounds(0, 0, 567, 305);	
		
		//Un-nuevo-destino
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Un-nuevo-destino.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		UnNuevoDestino.setIcon(new ImageIcon(img));
		UnNuevoDestino.setBounds(0, 0, 567, 305);	
		
		//Un-robot-asesino-de-zombies
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Un-robot-asesino-de-zombies.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		RobotAsesino.setIcon(new ImageIcon(img));
		RobotAsesino.setBounds(0, 0, 567, 305);	
		
		//Venganza
		ima = new ImageIcon(this.getClass().getResource("/Objetivos-secundarios/Venganza.jpg"));
		img = ima.getImage().getScaledInstance(567, 305, java.awt.Image.SCALE_SMOOTH); 
		
		Venganza.setIcon(new ImageIcon(img));
		Venganza.setBounds(0, 0, 567, 305);	
		
		
	}
	//TODO: AsocObjetos
	private HashMap<Integer, JLabel> AsocObjetos() {
		HashMap<Integer,JLabel> aux = new HashMap<Integer, JLabel>();
		JLabel comida1 = new JLabel(""), comida2 = new JLabel(""), comida3 = new JLabel(""), medicina = new JLabel("")
				, gasolina = new JLabel(""), trastos = new JLabel(""), sup1 = new JLabel(""), sup2 = new JLabel(""), 
				sup3 = new JLabel(""), francotirador = new JLabel(""), pistola = new JLabel(""), escopeta = new JLabel("")
				, pSuper = new JLabel(""), pComisaria = new JLabel(""), pGasolinera = new JLabel(""), pHospital = new JLabel("")
				, pBiblio = new JLabel(""), pColegio = new JLabel(""), libArtesMarciales = new JLabel(""), libDadoMas = new JLabel("")
				, libFBarricada = new JLabel(""), libNoMov = new JLabel(""), libMasComida = new JLabel("");
		IniciarLabelsObjetos(comida1, comida2, comida3, medicina, gasolina, trastos, sup1, sup2, sup3, francotirador, 
				pistola, escopeta, pSuper, pComisaria, pGasolinera, pHospital, pBiblio, pColegio, libArtesMarciales, 
				libDadoMas, libFBarricada, libNoMov, libMasComida);
		//Comida 1 -> 0
		aux.put(0, comida1);
		//Comida 2 -> 1
		aux.put(1, comida2);
		//Comida 3 -> 2
		aux.put(2, comida3);
		//Medicina -> 3
		aux.put(3, medicina);
		//Trastos -> 4
		aux.put(4, trastos);
		//Gasolina -> 5
		aux.put(5, gasolina);
		//Superviviente 1 -> 6
		aux.put(6, sup1);
		//Superviviente 2 -> 7
		aux.put(7, sup2);
		//Superviviente 3 -> 8
		aux.put(8, sup3);
		//Francotirador -> 9
		aux.put(9, francotirador);
		//Pistola -> 10
		aux.put(10, pistola);
		//Escopeta -> 11
		aux.put(11, escopeta);
		//pSuper -> 12
		aux.put(12, pSuper);
		//pComisaria -> 13
		aux.put(13, pComisaria);
		//pGasolinera -> 14
		aux.put(14, pGasolinera);
		//pHospital -> 15
		aux.put(15, pHospital);
		//pBiblio -> 16
		aux.put(16, pBiblio);
		//pColegio -> 17
		aux.put(17, pColegio);
		//libArtesMarciales -> 18
		aux.put(18, libArtesMarciales);
		//libDadosMas -> 19
		aux.put(19, libDadoMas);
		//libFBarricada -> 20
		aux.put(20, libFBarricada);
		//libNoMov -> 21
		aux.put(21, libNoMov);
		//libMasComida -> 22
		aux.put(22, libMasComida);
		return aux;
	}
	//TODO: IniciarLabelsObjetos
	private void IniciarLabelsObjetos(JLabel comida1, JLabel comida2, JLabel comida3, JLabel medicina, JLabel gasolina,
			JLabel trastos, JLabel sup1, JLabel sup2, JLabel sup3, JLabel francotirador, JLabel pistola, JLabel escopeta
			, JLabel pSuper, JLabel pComisaria, JLabel pGasolinera, JLabel pHospital, JLabel pBiblio, JLabel pColegio, 
			JLabel libArtesMarciales, JLabel libDadoMas, JLabel libFBarricada, JLabel libNoMov, JLabel libMasComida) {
		//Comida 1
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Objetos-iniciales/Alimento1.jpg"));
		Image img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		comida1.setIcon(new ImageIcon(img));
		comida1.setBounds(0, 0, 406, 517);
		//Comida 2
		
		ima = new ImageIcon(this.getClass().getResource("/Objetos-iniciales/Alimento2.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
	
		comida2.setIcon(new ImageIcon(img));
		comida2.setBounds(0, 0, 406, 517);
		//Comida 3
		
		ima = new ImageIcon(this.getClass().getResource("/Objetos-iniciales/Alimento3.jpg"));
		img = ima.getImage().getScaledInstance(406,517, java.awt.Image.SCALE_SMOOTH); 
		

		comida3.setIcon(new ImageIcon(img));
		comida3.setBounds(0, 0, 406, 517);
		//Medicina
		
		ima = new ImageIcon(this.getClass().getResource("/Objetos-iniciales/Medicina.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
	
		medicina.setIcon(new ImageIcon(img));
		medicina.setBounds(0, 0, 406, 517);
		//Gasolina
		
		ima = new ImageIcon(this.getClass().getResource("/Objetos-iniciales/Combustible.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		gasolina.setIcon(new ImageIcon(img));
		gasolina.setBounds(0, 0, 406, 517);
		//Trastos
		
		ima = new ImageIcon(this.getClass().getResource("/Objetos-iniciales/Trastos.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); //Bounds ajustados al boton
		
		
		trastos.setIcon(new ImageIcon(img));
		trastos.setBounds(0, 0, 406, 517);
		//Supervivientes 1
		
		ima = new ImageIcon(this.getClass().getResource("/GanarSuperviviente/Superviviente1.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		sup1.setIcon(new ImageIcon(img));
		sup1.setBounds(0, 0, 406,517);
		//Supervivientes 2
		
		ima = new ImageIcon(this.getClass().getResource("/GanarSuperviviente/Superviviente2.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		sup2.setIcon(new ImageIcon(img));
		sup2.setBounds(0, 0, 406, 517);
		//Supervivientes 3
		
		ima = new ImageIcon(this.getClass().getResource("/GanarSuperviviente/Superviviente3.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		sup3.setIcon(new ImageIcon(img));
		sup3.setBounds(0, 0, 406, 517);
		
		
		//Equipables//
		
		//Francotirador
		
		ima = new ImageIcon(this.getClass().getResource("/Armas/Rifle-Tactico.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		francotirador.setIcon(new ImageIcon(img));
		francotirador.setBounds(0, 0, 406, 517);
		//Pistola
		
		ima = new ImageIcon(this.getClass().getResource("/Armas/Pistola-Colt.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		pistola.setIcon(new ImageIcon(img));
		pistola.setBounds(0, 0, 406, 517);
		//Escopeta
		
		ima = new ImageIcon(this.getClass().getResource("/Armas/Escopeta-Fn-Slp.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		escopeta.setIcon(new ImageIcon(img));
		escopeta.setBounds(0, 0, 406, 517);
		//Plano Supermercado
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Planos-Supermercado.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		pSuper.setIcon(new ImageIcon(img));
		pSuper.setBounds(0, 0, 406, 517);
		//Plano Comisaria
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Planos-Comisaria.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		pComisaria.setIcon(new ImageIcon(img));
		pComisaria.setBounds(0, 0, 406, 517);
		//Plano Hospital
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Planos-Hospital.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		pHospital.setIcon(new ImageIcon(img));
		pHospital.setBounds(0, 0, 406, 517);
		//Plano Colegio
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/PlanosColegio.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		pColegio.setIcon(new ImageIcon(img));
		pColegio.setBounds(0, 0, 406, 517);
		//Plano Biblioteca
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Planos-Biblioteca.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		pBiblio.setIcon(new ImageIcon(img));
		pBiblio.setBounds(0, 0, 406, 517);
		//Plano Gasolinera
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Planos-Gasolinera.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		pGasolinera.setIcon(new ImageIcon(img));
		pGasolinera.setBounds(0, 0, 406, 517);
		//Libro Artes Marciales(no tira al atacar)
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Artes-MarcialesPrincipiantes.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		libArtesMarciales.setIcon(new ImageIcon(img));
		libArtesMarciales.setBounds(0, 0, 406, 517);
		//Libro Guia para el liderazgo(aumenta dado)
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Guia-para-liderazgo.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
	
		libDadoMas.setIcon(new ImageIcon(img));
		libDadoMas.setBounds(0, 0, 406, 517);
		//Libro 1, 2, 3 Barricadas (contruir barricada free)
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/123Barricadas.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
	
		libFBarricada.setIcon(new ImageIcon(img));
		libFBarricada.setBounds(0, 0, 406, 517);
		//Libro Volver Tirar Dado(no tirar al moverse)
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Viaje-Jazzercise.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		libNoMov.setIcon(new ImageIcon(img));
		libNoMov.setBounds(0, 0, 406, 517);
		//Libro Poner Comida
		
		ima = new ImageIcon(this.getClass().getResource("/Libros/Libro-CocinaSuperviviente.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		
		libMasComida.setIcon(new ImageIcon(img));
		libMasComida.setBounds(0, 0, 406, 517);
		
	}
	
	private HashMap<Integer, JLabel> AsocCrisis() {
		HashMap<Integer,JLabel> aux = new HashMap<Integer, JLabel>();
		JLabel agotamiento = new JLabel("");
		JLabel despensaVacia = new JLabel("");
		JLabel enfermedad = new JLabel("");
		JLabel escasezDeGasolina= new JLabel("");
		JLabel horrorNoche = new JLabel("");
		JLabel nosEstanObservando = new JLabel("");
		JLabel oleadaZombis = new JLabel("");
		JLabel reorganizacion = new JLabel("");
		iniciarLabelsCrisis(agotamiento, despensaVacia, enfermedad, escasezDeGasolina, horrorNoche, nosEstanObservando, oleadaZombis, reorganizacion);
		//Comida 1 -> 0
		aux.put(300, agotamiento);
		//Comida 2 -> 1
		aux.put(301, despensaVacia);
		//Comida 3 -> 2
		aux.put(302, enfermedad);
		//Medicina -> 3
		aux.put(303, escasezDeGasolina);
		//Trastos -> 4
		aux.put(304, horrorNoche);
		//Gasolina -> 5
		aux.put(305, nosEstanObservando);
		//Superviviente 1 -> 6
		aux.put(306, oleadaZombis);
		//Superviviente 2 -> 7
		aux.put(307, reorganizacion);

		return aux;
	}
	
	private void iniciarLabelsCrisis(JLabel agotamiento,JLabel despensaVacia,JLabel enfermedad,JLabel escasezDeGasolina,JLabel horrorNoche,JLabel nosEstanObservando,JLabel oleadaZombis,JLabel reorganizacion) {
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Crisis/Agotamiento.jpg"));
		Image img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		agotamiento.setIcon(new ImageIcon(img));
		agotamiento.setBounds(0, 0, 406, 517);
		
		ima = new ImageIcon(this.getClass().getResource("/Crisis/Despensa-vacia.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		despensaVacia.setIcon(new ImageIcon(img));
		despensaVacia.setBounds(0, 0, 406, 517);
		
		ima = new ImageIcon(this.getClass().getResource("/Crisis/Enfermedad.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		enfermedad.setIcon(new ImageIcon(img));
		enfermedad.setBounds(0, 0, 406, 517);
		
		ima = new ImageIcon(this.getClass().getResource("/Crisis/EscasezDeGasolina.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		escasezDeGasolina.setIcon(new ImageIcon(img));
		escasezDeGasolina.setBounds(0, 0, 406, 517);
		
		ima = new ImageIcon(this.getClass().getResource("/Crisis/Horror-Noche.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		horrorNoche.setIcon(new ImageIcon(img));
		horrorNoche.setBounds(0, 0, 406, 517);
		
		ima = new ImageIcon(this.getClass().getResource("/Crisis/Nos-Estan-Observando.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		nosEstanObservando.setIcon(new ImageIcon(img));
		nosEstanObservando.setBounds(0, 0, 406, 517);
		
		ima = new ImageIcon(this.getClass().getResource("/Crisis/Oleada-Zombis.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		oleadaZombis.setIcon(new ImageIcon(img));
		oleadaZombis.setBounds(0, 0, 406, 517);
		
		ima = new ImageIcon(this.getClass().getResource("/Crisis/Reorganizacion.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		reorganizacion.setIcon(new ImageIcon(img));
		reorganizacion.setBounds(0, 0, 406, 517);
				
		
	}
	private HashMap<Integer, JLabel> AsocHerramientas(){
		HashMap<Integer,JLabel> aux = new HashMap<Integer, JLabel>();
		JLabel bateBeisbol = new JLabel(""), candado = new JLabel(""), carnePodrida = new JLabel(""), fregona = new JLabel("")
				, gafasNocturnas = new JLabel(""), lamparaOficina = new JLabel(""), linterna = new JLabel(""), llaveInglesa = new JLabel(""), 
				martillo = new JLabel(""), megafono = new JLabel(""), raquetasNieve = new JLabel(""), tijeras = new JLabel("")
				, trastos = new JLabel("");
		IniciarHerramientas(bateBeisbol, candado, carnePodrida, fregona, gafasNocturnas, lamparaOficina, linterna, llaveInglesa, martillo,
				megafono, raquetasNieve, tijeras, trastos);
		//BateBeisbol -> 23
		aux.put(23, bateBeisbol);
		//Candado-> 24
		aux.put(24, candado);
		//CarnePodrida-> 25
		aux.put(25, carnePodrida);
		//Fregona ->26
		aux.put(26, fregona);
		//GafasNocturnas-> 27
		aux.put(27, gafasNocturnas);
		//LamparaOficina > 28
		aux.put(28, lamparaOficina);
		//Linterna -> 29
		aux.put(29, linterna);
		//LlaveInglesa-> 30
		aux.put(30, llaveInglesa);
		//Martillo -> 31
		aux.put(31, martillo);
		//Megafono -> 31
		aux.put(9, megafono);
		//RaquetasNieve -> 32
		aux.put(32, raquetasNieve);
		//Tijeras -> 33
		aux.put(33, tijeras);
		//Trastos -> 34
		aux.put(34, trastos);

		return aux;
	}
	
	private void IniciarHerramientas(JLabel bateBeisbol, JLabel candado, JLabel carnePodrida, JLabel fregona, JLabel gafasNocturnas, 
			JLabel lamparaOficina, JLabel literna, JLabel llaveInglesa, JLabel martillo, JLabel megafono, JLabel raquetasNieve, 
			JLabel tijeras, JLabel trastos) {
		//Bate-Beisbol
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Herramientas/Bate-Beisbol.jpg"));
		Image img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		bateBeisbol.setIcon(new ImageIcon(img));
		bateBeisbol.setBounds(0, 0, 406, 517);	
		
		//Candado
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Candado.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		candado.setIcon(new ImageIcon(img));
		candado.setBounds(0, 0, 406, 517);
		
		//CarnePodrida
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/CarnePodrida.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		carnePodrida.setIcon(new ImageIcon(img));
		carnePodrida.setBounds(0, 0, 406, 517);
		
		//Fregona
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Fregona.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		fregona.setIcon(new ImageIcon(img));
		fregona.setBounds(0, 0, 406, 517);
		
		//GafasVisionNocturna
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Gafas-VisionNocturna.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		gafasNocturnas.setIcon(new ImageIcon(img));
		gafasNocturnas.setBounds(0, 0, 406, 517);
		
		//LamparaOficina
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Lampara-Oficina.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		lamparaOficina.setIcon(new ImageIcon(img));
		lamparaOficina.setBounds(0, 0, 406, 517);
		
		//Linterna
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Linterna.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		literna.setIcon(new ImageIcon(img));
		literna.setBounds(0, 0, 406, 517);
		
		//LLaveInglesa
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/LLaveInglesa.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		llaveInglesa.setIcon(new ImageIcon(img));
		llaveInglesa.setBounds(0, 0, 406, 517);
		
		//Megafono
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Megafono.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		megafono.setIcon(new ImageIcon(img));
		megafono.setBounds(0, 0, 406, 517);
		
		//RaquetasNieve
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Raquetas-Nieve.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		raquetasNieve.setIcon(new ImageIcon(img));
		raquetasNieve.setBounds(0, 0, 406, 517);
		//Tijeras
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Tijeras.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		tijeras.setIcon(new ImageIcon(img));
		tijeras.setBounds(0, 0, 406, 517);
		
		//Trastos
		ima = new ImageIcon(this.getClass().getResource("/Herramientas/Trastos.jpg"));
		img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		trastos.setIcon(new ImageIcon(img));
		trastos.setBounds(0, 0, 406, 517);
		
	}
	
	public HashMap<Integer, JLabel> AsocDados(){
		HashMap<Integer,JLabel> aux = new HashMap<Integer, JLabel>();
		JLabel dado1 = new JLabel("");
		JLabel dado2 = new JLabel("");
		JLabel dado3 = new JLabel("");
		JLabel dado4 = new JLabel("");
		JLabel dado5 = new JLabel("");
		JLabel dado6 = new JLabel("");
		iniciarDados(dado1, dado2, dado3, dado4,dado5, dado6);
		//Dados > 400
		aux.put(400,dado1);
		aux.put(401,dado2);
		aux.put(402,dado3);
		aux.put(403,dado4);
		aux.put(404,dado5);
		aux.put(405,dado6);
		
		return aux;
	}
	
	public void iniciarDados(JLabel dado1, JLabel dado2, JLabel dado3, JLabel dado4, JLabel dado5, JLabel dado6){
		
		//Dado 1
		ImageIcon ima = new ImageIcon(this.getClass().getResource("/Dados/Dado1.png"));
		Image img = ima.getImage().getScaledInstance(406, 517, java.awt.Image.SCALE_SMOOTH); 
		
		dado1.setIcon(new ImageIcon(img));
		dado1.setBounds(0, 0, 165, 170);
		
		//Dado 2
		ima = new ImageIcon(this.getClass().getResource("/Dados/Dado2.png"));
		img = ima.getImage().getScaledInstance(165, 170, java.awt.Image.SCALE_SMOOTH); 
		
		dado2.setIcon(new ImageIcon(img));
		dado2.setBounds(0, 0, 165, 170);
		
		//Dado 3
		ima = new ImageIcon(this.getClass().getResource("/Dados/Dado3.png"));
		img = ima.getImage().getScaledInstance(165, 170, java.awt.Image.SCALE_SMOOTH); 
		
		dado3.setIcon(new ImageIcon(img));
		dado3.setBounds(0, 0, 165, 170);
		
		//Dado 4
		ima = new ImageIcon(this.getClass().getResource("/Dados/Dado4.png"));
		img = ima.getImage().getScaledInstance(165, 170, java.awt.Image.SCALE_SMOOTH); 
		
		dado4.setIcon(new ImageIcon(img));
		dado4.setBounds(0, 0, 165, 170);
		
		//Dado 5
		ima = new ImageIcon(this.getClass().getResource("/Dados/Dado5.png"));
		img = ima.getImage().getScaledInstance(165, 170, java.awt.Image.SCALE_SMOOTH); 
		
		dado5.setIcon(new ImageIcon(img));
		dado5.setBounds(0, 0, 165, 170);
		
		//Dado 6
		ima = new ImageIcon(this.getClass().getResource("/Dados/Dado6.png"));
		img = ima.getImage().getScaledInstance(165, 170, java.awt.Image.SCALE_SMOOTH); 
		
		dado6.setIcon(new ImageIcon(img));
		dado6.setBounds(0, 0, 165, 170);
	}

	
	//TODO: Getters
	public HashMap<Integer,JLabel> getCartasObjetos(){
		return CartasObjetos;
	}
	public HashMap<Integer,JLabel> getObjSecretos(){
		return ObjetivoSecreto;
	}
	public HashMap<Integer,JLabel[]> getSupMap(){
		return Supervivientes;
	}
	public HashMap<Integer,JLabel> getCartasCrisis(){
		return CartasCrisis;
	}
}
