package LegendaryCardMaker.LegendaryHeroMaker;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import LegendaryCardMaker.CardTextDialog;
import LegendaryCardMaker.Icon;
import LegendaryCardMaker.JFontChooser;
import LegendaryCardMaker.LegendaryCardMaker;
import LegendaryCardMaker.LegendaryCardMakerFrame;
import LegendaryCardMaker.Messages;
import LegendaryCardMaker.LegendaryHeroMaker.HeroMaker.DUAL_CLASS_STYLE;

public class HeroMakerToolbar extends JMenuBar implements ActionListener{

	HeroMaker hm;
	HeroMakerFrame hmf;
	
	JMenu file = new JMenu(Messages.getString("Toolbar.File"));
	
	JMenuItem exportJPG = new JMenuItem(Messages.getString("Toolbar.ExportJPEG"));
	JMenuItem exportPNG = new JMenuItem(Messages.getString("Toolbar.ExportPNG"));
	JMenuItem exportPrinterStudioPNG = new JMenuItem(Messages.getString("Toolbar.ExportBorderedPNG"));
	JMenuItem close = new JMenuItem(Messages.getString("Toolbar.Close"));
	
	JMenu edit = new JMenu(Messages.getString("Toolbar.Edit"));
	
	JMenu rarity = new JMenu(Messages.getString("Card.CardRarity"));
	JCheckBoxMenuItem rarityCommon = new JCheckBoxMenuItem(Messages.getString("Card.COMMON"));
	JCheckBoxMenuItem rarityUncommon = new JCheckBoxMenuItem(Messages.getString("Card.PEU_COMMUNE"));
	JCheckBoxMenuItem rarityRare = new JCheckBoxMenuItem(Messages.getString("Card.RARE"));
	
	JMenu team = new JMenu(Messages.getString("Card.Team"));
	List<JCheckBoxMenuItem> teamItems = new ArrayList<JCheckBoxMenuItem>();
	
	JMenu secondaryTeam = new JMenu(Messages.getString("Card.SecondaryTeam"));
	List<JCheckBoxMenuItem> secondaryTeamItems = new ArrayList<JCheckBoxMenuItem>();
	
	JMenu power = new JMenu(Messages.getString("Card.Power"));
	List<JCheckBoxMenuItem> powerItems = new ArrayList<JCheckBoxMenuItem>();
	
	JMenu secondaryPower = new JMenu(Messages.getString("Card.SecondaryPower"));
	List<JCheckBoxMenuItem> secondaryPowerItems = new ArrayList<JCheckBoxMenuItem>();
	
	JMenu dualStyle = new JMenu(Messages.getString("Card.DualPowerIconStyle"));
	JCheckBoxMenuItem dualStyleSideBySide = new JCheckBoxMenuItem(Messages.getString("Card.SideSide"));
	JCheckBoxMenuItem dualStyleHalfAndHalf = new JCheckBoxMenuItem(Messages.getString("Card.HalfHalf"));
	JCheckBoxMenuItem dualStyleTopAndBottom = new JCheckBoxMenuItem(Messages.getString("Card.TopBottom"));
	
	JMenuItem setCost = new JMenuItem(Messages.getString("Toolbar.SetCost"));
	JMenuItem setRecruit = new JMenuItem(Messages.getString("Toolbar.SetRecruit"));
	JMenuItem setAttack = new JMenuItem(Messages.getString("Toolbar.SetAttack"));
	JMenuItem setCardName = new JMenuItem(Messages.getString("Toolbar.SetCardName"));
	JMenuItem setCardNameSize = new JMenuItem(Messages.getString("Toolbar.SetCardNameSize"));
	JMenuItem setHeroNameSize = new JMenuItem(Messages.getString("Toolbar.SetHeroNameSize"));
	JMenuItem setAbilityText = new JMenuItem(Messages.getString("Toolbar.SetAbilityText"));
	JMenuItem setAbilityTextSize = new JMenuItem(Messages.getString("Toolbar.SetAbilityTextSize"));
	JMenuItem setBackgroundImage = new JMenuItem(Messages.getString("Toolbar.SetBackgroundImage"));
	JMenuItem setBackgroundZoom = new JMenuItem(Messages.getString("Toolbar.SetBackgroundZoom"));
	
	JMenuItem setTeamPowerColour = new JMenuItem(Messages.getString("Toolbar.SetTeamPowerUnderlayColour"));
	JMenuItem setTeamPowerRadius = new JMenuItem(Messages.getString("Toolbar.SetTeamPowerBlurRadius"));
	JMenuItem setCardNameColour = new JMenuItem(Messages.getString("Toolbar.SetCardNameColour"));
	JMenuItem setCardNameFont = new JMenuItem(Messages.getString("Toolbar.SetCardNameFont"));
	JMenuItem setHeroNameColour = new JMenuItem(Messages.getString("Toolbar.SetHeroNameColour"));
	JMenuItem setHeroNameFont = new JMenuItem(Messages.getString("Toolbar.SetHeroNameFont"));
	JMenuItem setAbilityTextColour = new JMenuItem(Messages.getString("Toolbar.SetAbilityTextColour"));
	JMenuItem setAbilityTextFont = new JMenuItem(Messages.getString("Toolbar.SetAbilityTextFont"));
	
	JMenuItem setCostSize = new JMenuItem(Messages.getString("Toolbar.SetCostSize"));
	JMenuItem setCostColour = new JMenuItem(Messages.getString("Toolbar.SetCostColour"));
	JMenuItem setCostFont = new JMenuItem(Messages.getString("Toolbar.SetCostFont"));
	
	JMenuItem setRecruitSize = new JMenuItem(Messages.getString("Toolbar.SetRecruitSize"));
	JMenuItem setRecruitColour = new JMenuItem(Messages.getString("Toolbar.SetRecruitColour"));
	JMenuItem setRecruitFont = new JMenuItem(Messages.getString("Toolbar.SetRecruitFont"));
	
	JMenuItem setAttackSize = new JMenuItem(Messages.getString("Toolbar.SetAttackSize"));
	JMenuItem setAttackColour = new JMenuItem(Messages.getString("Toolbar.SetAttackColour"));
	JMenuItem setAttackFont = new JMenuItem(Messages.getString("Toolbar.SetAttackFont"));
	
	JMenu nameHighlightType = new JMenu(Messages.getString("Toolbar.NameHighlightType"));
	JCheckBoxMenuItem highlightBlur = new JCheckBoxMenuItem(Messages.getString("Toolbar.Blur"));
	JCheckBoxMenuItem highlightBanner = new JCheckBoxMenuItem(Messages.getString("Toolbar.Banner"));
	
	JMenuItem resetTemplate = new JMenuItem(Messages.getString("Toolbar.ResetTemplate"));
	
	JMenuItem setNumberInDeck = new JMenuItem(Messages.getString("Toolbar.SetNumberInDeck"));
	
	JCheckBoxMenuItem multiClass = new JCheckBoxMenuItem(Messages.getString("Toolbar.MultiClassPreview"));
	
	static HeroMakerToolbar tb = null;
	
	public HeroMakerToolbar(HeroMaker hm, HeroMakerFrame hmf)
	{
		tb = this;
		
		this.hm = hm;
		this.hmf = hmf;
		
		exportJPG.addActionListener(this);
		//file.add(exportJPG);
		exportPNG.addActionListener(this);
		file.add(exportPNG);
		
		exportPrinterStudioPNG.addActionListener(this);
		file.add(exportPrinterStudioPNG);
		
		file.addSeparator();
		
		close.addActionListener(this);
		file.add(close);
		
		add(file);
		
		if (hm.card.rarity.equals(CardRarity.COMMON)) { rarityCommon.setSelected(true); }
		rarityCommon.addActionListener(this);
		rarity.add(rarityCommon);
		if (hm.card.rarity.equals(CardRarity.UNCOMMON)) { rarityUncommon.setSelected(true); }
		rarityUncommon.addActionListener(this);
		rarity.add(rarityUncommon);
		if (hm.card.rarity.equals(CardRarity.RARE)) { rarityRare.setSelected(true); }
		rarityRare.addActionListener(this);
		rarity.add(rarityRare);
		edit.add(rarity);
		
		edit.addSeparator();
		
		if (!hmf.templateMode)
		{
			for (Icon icon : Icon.values())
			{
				if (icon.getIconType().equals(Icon.ICON_TYPE.TEAM) || icon.getIconType().equals(Icon.ICON_TYPE.NONE))
				{
					String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
					s = s.replace("_", " ");
					JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
					m.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
							Icon icon = Icon.valueOf(enumValue);
							tb.hm.card.cardTeam = icon;
							
							for (JCheckBoxMenuItem item : tb.teamItems)
							{
								if (item.getText().replace(" ", "_").toUpperCase().equals(enumValue))
								{
									item.setSelected(true);
								}
								else
								{
									item.setSelected(false);
								}
							}
							
							for (Hero h : LegendaryCardMakerFrame.lcmf.lcm.heroes)
							{
								if (h.name.equals(tb.hm.card.heroName))
								{
									h.imageSummary = null;
								}
							}
							tb.hm.card.imageSummary = null;
							
							tb.hmf.reRenderCard();
							tb.hm.card.changed = true;
						}
					});
					
					teamItems.add(m);
					team.add(m);
				}
				
				if (icon.getIconType().equals(Icon.ICON_TYPE.TEAM) || icon.getIconType().equals(Icon.ICON_TYPE.NONE))
				{
					String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
					s = s.replace("_", " ");
					JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
					m.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
							Icon icon = Icon.valueOf(enumValue);
							tb.hm.card.cardTeam2 = icon;
							
							for (JCheckBoxMenuItem item : tb.secondaryTeamItems)
							{
								if (item.getText().replace(" ", "_").toUpperCase().equals(enumValue))
								{
									item.setSelected(true);
								}
								else
								{
									item.setSelected(false);
								}
							}
							
							for (Hero h : LegendaryCardMakerFrame.lcmf.lcm.heroes)
							{
								if (h.name.equals(tb.hm.card.heroName))
								{
									h.imageSummary = null;
								}
							}
							tb.hm.card.imageSummary = null;
							
							tb.hmf.reRenderCard();
							tb.hm.card.changed = true;
						}
					});
					
					secondaryTeamItems.add(m);
					secondaryTeam.add(m);
				}
				
				if (icon.getIconType().equals(Icon.ICON_TYPE.POWER) || icon.getIconType().equals(Icon.ICON_TYPE.NONE))
				{
					String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
					s.replace("_", " ");
					JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
					m.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
							Icon icon = Icon.valueOf(enumValue);
							tb.hm.card.cardPower = icon;
							
							for (JCheckBoxMenuItem item : tb.powerItems)
							{
								if (item.getText().replace(" ", "_").toUpperCase().equals(enumValue))
								{
									item.setSelected(true);
								}
								else
								{
									item.setSelected(false);
								}
							}
							
							for (Hero h : LegendaryCardMakerFrame.lcmf.lcm.heroes)
							{
								if (h.name.equals(tb.hm.card.heroName))
								{
									h.imageSummary = null;
								}
							}
							tb.hm.card.imageSummary = null;
							
							tb.hmf.reRenderCard();
							tb.hm.card.changed = true;
						}
					});
					
					powerItems.add(m);
					power.add(m);
				}
				
				if (icon.getIconType().equals(Icon.ICON_TYPE.POWER) || icon.getIconType().equals(Icon.ICON_TYPE.NONE))
				{
					String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
					s.replace("_", " ");
					JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
					m.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
							Icon icon = Icon.valueOf(enumValue);
							tb.hm.card.cardPower2 = icon;
							
							for (JCheckBoxMenuItem item : tb.secondaryPowerItems)
							{
								if (item.getText().replace(" ", "_").toUpperCase().equals(enumValue))
								{
									item.setSelected(true);
								}
								else
								{
									item.setSelected(false);
								}
							}
							
							tb.hmf.reRenderCard();
							tb.hm.card.changed = true;
						}
					});
					
					secondaryPowerItems.add(m);
					secondaryPower.add(m);
				}
			}
			
			for (JCheckBoxMenuItem item : teamItems)
			{
				if (hm.card.cardTeam != null && item.getText().replace(" ", "_").toUpperCase().equals(hm.card.cardTeam.toString()))
				{
					item.setSelected(true);
				}
				else
				{
					item.setSelected(false);
				}
			}
			edit.add(team);
			
			for (JCheckBoxMenuItem item : secondaryTeamItems)
			{
				if (hm.card.cardTeam2 != null && item.getText().replace(" ", "_").toUpperCase().equals(hm.card.cardTeam2.toString()))
				{
					item.setSelected(true);
				}
				else
				{
					item.setSelected(false);
				}
			}
			edit.add(secondaryTeam);
			
			edit.addSeparator();
			
			for (JCheckBoxMenuItem item : powerItems)
			{
				if (hm.card.cardPower != null && item.getText().replace(" ", "_").toUpperCase().equals(hm.card.cardPower.toString()))
				{
					item.setSelected(true);
				}
				else
				{
					item.setSelected(false);
				}
			}
			edit.add(power);
			
			for (JCheckBoxMenuItem item : secondaryPowerItems)
			{
				if (hm.card.cardPower2 != null && item.getText().replace(" ", "_").toUpperCase().equals(hm.card.cardPower2.toString()))
				{
					item.setSelected(true);
				}
				else
				{
					item.setSelected(false);
				}
			}
			edit.add(secondaryPower);
			
			edit.addSeparator();
			
			setCardName.addActionListener(this);
			edit.add(setCardName);
			
			setCardNameSize.addActionListener(this);
			edit.add(setCardNameSize);
			
			setHeroNameSize.addActionListener(this);
			edit.add(setHeroNameSize);
			
			edit.addSeparator();
			
			setRecruit.addActionListener(this);
			edit.add(setRecruit);
			
			setAttack.addActionListener(this);
			edit.add(setAttack);
			
			setCost.addActionListener(this);
			edit.add(setCost);
			
			edit.addSeparator();
			
			setAbilityText.addActionListener(this);
			edit.add(setAbilityText);
			
			setAbilityTextSize.addActionListener(this);
			edit.add(setAbilityTextSize);
			
			edit.addSeparator();
			
			setBackgroundImage.addActionListener(this);
			edit.add(setBackgroundImage);
			
			setBackgroundZoom.addActionListener(this);
			edit.add(setBackgroundZoom);
			
			edit.addSeparator();
			
			setNumberInDeck.addActionListener(this);
			edit.add(setNumberInDeck);
		}
		else
		{
			
			setTeamPowerColour.addActionListener(this);
			edit.add(setTeamPowerColour);
			
			setTeamPowerRadius.addActionListener(this);
			edit.add(setTeamPowerRadius);
			
			multiClass.addActionListener(this);
			edit.add(multiClass);
			
			if (HeroMaker.dualClassStyle.equals(HeroMaker.DUAL_CLASS_STYLE.SIDE_BY_SIDE)) { dualStyleSideBySide.setSelected(true); }
			dualStyleSideBySide.addActionListener(this);
			dualStyle.add(dualStyleSideBySide);
			if (HeroMaker.dualClassStyle.equals(HeroMaker.DUAL_CLASS_STYLE.HALF_AND_HALF)) { dualStyleHalfAndHalf.setSelected(true); }
			dualStyleHalfAndHalf.addActionListener(this);
			dualStyle.add(dualStyleHalfAndHalf);
			if (HeroMaker.dualClassStyle.equals(HeroMaker.DUAL_CLASS_STYLE.TOP_AND_BOTTOM)) { dualStyleTopAndBottom.setSelected(true); }
			dualStyleTopAndBottom.addActionListener(this);
			dualStyle.add(dualStyleTopAndBottom);
			edit.add(dualStyle);
			
			edit.addSeparator();
			
			if (hm.nameHighlight.equals(Messages.getString("Toolbar.Blur"))) { highlightBlur.setSelected(true); }
			highlightBlur.addActionListener(this);
			nameHighlightType.add(highlightBlur);
			if (hm.nameHighlight.equals(Messages.getString("Toolbar.Banner"))) { highlightBanner.setSelected(true); }
			highlightBanner.addActionListener(this);
			nameHighlightType.add(highlightBanner);
			edit.add(nameHighlightType);
			
			setCardNameSize.addActionListener(this);
			edit.add(setCardNameSize);
			
			setCardNameColour.addActionListener(this);
			edit.add(setCardNameColour);
			
			setCardNameFont.addActionListener(this);
			edit.add(setCardNameFont);

			edit.addSeparator();
			
			setHeroNameSize.addActionListener(this);
			edit.add(setHeroNameSize);
			
			setHeroNameColour.addActionListener(this);
			edit.add(setHeroNameColour);
			
			setHeroNameFont.addActionListener(this);
			edit.add(setHeroNameFont);
			
			edit.addSeparator();
			
			setAbilityTextSize.addActionListener(this);
			edit.add(setAbilityTextSize);
			
			setAbilityTextColour.addActionListener(this);
			edit.add(setAbilityTextColour);
			
			setAbilityTextFont.addActionListener(this);
			edit.add(setAbilityTextFont);
			
			edit.addSeparator();
			
			setCostSize.addActionListener(this);
			edit.add(setCostSize);
			
			setCostColour.addActionListener(this);
			edit.add(setCostColour);
			
			setCostFont.addActionListener(this);
			edit.add(setCostFont);
			
			edit.addSeparator();
			
			setRecruitSize.addActionListener(this);
			edit.add(setRecruitSize);
			
			setRecruitColour.addActionListener(this);
			edit.add(setRecruitColour);
			
			setRecruitFont.addActionListener(this);
			edit.add(setRecruitFont);
			
			edit.addSeparator();
			
			setAttackSize.addActionListener(this);
			edit.add(setAttackSize);
			
			setAttackColour.addActionListener(this);
			edit.add(setAttackColour);
			
			setAttackFont.addActionListener(this);
			edit.add(setAttackFont);
			
			edit.addSeparator();
			
			resetTemplate.addActionListener(this);
			edit.add(resetTemplate);
		}
		
		add(edit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(rarityCommon))
		{
			rarityCommon.setSelected(true);
			rarityUncommon.setSelected(false);
			rarityRare.setSelected(false);
			hm.card.rarity = CardRarity.COMMON;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		if (e.getSource().equals(rarityUncommon))
		{
			rarityCommon.setSelected(false);
			rarityUncommon.setSelected(true);
			rarityRare.setSelected(false);
			hm.card.rarity = CardRarity.UNCOMMON;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		if (e.getSource().equals(rarityRare))
		{
			rarityCommon.setSelected(false);
			rarityUncommon.setSelected(false);
			rarityRare.setSelected(true);
			hm.card.rarity = CardRarity.RARE;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(exportJPG))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
		    chooser.addChoosableFileFilter(filter1);
		    chooser.setFileFilter(filter1);
			int outcome = chooser.showSaveDialog(this);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				BufferedImage bi = hm.generateCard();
				try
				{
					if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".jpg") 
							&& !chooser.getSelectedFile().getName().toLowerCase().endsWith(".jpeg"))
					{
						chooser.setSelectedFile(new File(chooser.getSelectedFile().getAbsolutePath() + ".jpg"));
					}
					if (chooser.getSelectedFile().exists())
					{
						int confirm = JOptionPane.showConfirmDialog(hmf, "Overwrite File?", "File Exists", JOptionPane.YES_OPTION);
						if (confirm == JOptionPane.YES_OPTION)
						{
							hm.exportToJPEG(bi, chooser.getSelectedFile());
						}
					}
					else
					{
						hm.exportToJPEG(bi, chooser.getSelectedFile());
					}
					
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		}
		
		if (e.getSource().equals(exportPNG))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter("PNG file", "png");
		    chooser.addChoosableFileFilter(filter1);
		    chooser.setFileFilter(filter1);
			int outcome = chooser.showSaveDialog(this);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				BufferedImage bi = hm.generateCard();
				try
				{
					if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".png"))
					{
						chooser.setSelectedFile(new File(chooser.getSelectedFile().getAbsolutePath() + ".png"));
					}
					if (chooser.getSelectedFile().exists())
					{
						int confirm = JOptionPane.showConfirmDialog(hmf, "Overwrite File?", "File Exists", JOptionPane.YES_OPTION);
						if (confirm == JOptionPane.YES_OPTION)
						{
							hm.exportToPNG(bi, chooser.getSelectedFile());
						}
					}
					else
					{
						hm.exportToPNG(bi, chooser.getSelectedFile());
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		}
		
		if (e.getSource().equals(exportPrinterStudioPNG))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter("PNG file", "png");
		    chooser.addChoosableFileFilter(filter1);
		    chooser.setFileFilter(filter1);
			int outcome = chooser.showSaveDialog(this);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				BufferedImage bi = hm.generateCard();
				bi = hm.resizeImagePS(bi);
				try
				{
					if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".png"))
					{
						chooser.setSelectedFile(new File(chooser.getSelectedFile().getAbsolutePath() + ".png"));
					}
					if (chooser.getSelectedFile().exists())
					{
						int confirm = JOptionPane.showConfirmDialog(hmf, "Overwrite File?", "File Exists", JOptionPane.YES_OPTION);
						if (confirm == JOptionPane.YES_OPTION)
						{
							hm.exportToPNG(bi, chooser.getSelectedFile());
						}
					}
					else
					{
						hm.exportToPNG(bi, chooser.getSelectedFile());
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		}
		
		if (e.getSource().equals(setCost))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Cost", hm.card.cost);
			if (s == null) { s = hm.card.cost; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.cost = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setRecruit))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Recruit", hm.card.recruit);
			if (s == null) { s = hm.card.recruit; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.recruit = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setAttack))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Attack", hm.card.attack);
			if (s == null) { s = hm.card.attack; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.attack = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setAbilityText))
		{
			//String s = JOptionPane.showInputDialog(hmf, "Enter the Ability Text", hm.card.abilityText);
			String s = new CardTextDialog(hm.card.abilityText).showInputDialog();
			if (s == null) { s = hm.card.abilityText; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.abilityText = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardName))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Card Name", hm.card.name);
			if (s == null) { s = hm.card.name; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.name = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setAbilityTextSize))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Ability Text Size", hm.textSize);
			if (s == null) { s = "" + hm.textSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.textSize; }
			try
			{
				hm.textSize = Integer.parseInt(s);
				hm.card.abilityTextSize = Integer.parseInt(s);
				
				if (hmf.templateMode)
				{
					HeroMaker.abilityTextSizeTemplate = hm.textSize;
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setAbilityTextFont))
		{
			JFontChooser fontChooser = new JFontChooser();
			int outcome = fontChooser.showDialog(hmf);
			if (outcome == JFontChooser.OK_OPTION)
			{
				hm.textSize = fontChooser.getSelectedFontSize();
				hm.card.abilityTextSize = fontChooser.getSelectedFontSize();
				
				hm.textFontName = fontChooser.getSelectedFont().getFontName();
				hm.textFontStyle = fontChooser.getSelectedFont().getStyle();
				
				if (hmf.templateMode)
				{
					HeroMaker.abilityTextFontNameTemplate = hm.textFontName;
					HeroMaker.abilityTextFontStyleTemplate = hm.textFontStyle;
					HeroMaker.abilityTextSizeTemplate = fontChooser.getSelectedFontSize();
				}
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardNameSize))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Card Name Size", hm.cardNameSize);
			if (s == null) { s = "" + hm.cardNameSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.cardNameSize; }
			try
			{
				hm.cardNameSize = Integer.parseInt(s);
				hm.card.nameSize = Integer.parseInt(s);
				
				if (hmf.templateMode)
				{
					HeroMaker.cardNameSizeTemplate = hm.cardNameSize;
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardNameFont))
		{
			JFontChooser fontChooser = new JFontChooser();
			int outcome = fontChooser.showDialog(hmf);
			if (outcome == JFontChooser.OK_OPTION)
			{
				hm.cardNameSize = fontChooser.getSelectedFontSize();
				hm.card.nameSize = fontChooser.getSelectedFontSize();
				
				hm.cardNameFontName = fontChooser.getSelectedFont().getFontName();
				hm.cardNameFontStyle = fontChooser.getSelectedFont().getStyle();
				
				if (hmf.templateMode)
				{
					HeroMaker.cardNameFontNameTemplate = hm.cardNameFontName;
					HeroMaker.cardNameFontStyleTemplate = hm.cardNameFontStyle;
					HeroMaker.cardNameSizeTemplate = fontChooser.getSelectedFontSize();
				}
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setHeroNameSize))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Hero Name Size", hm.heroNameSize);
			if (s == null) { s = "" + hm.heroNameSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.heroNameSize; }
			try
			{
				hm.heroNameSize = Integer.parseInt(s);
				hm.card.heroNameSize = Integer.parseInt(s);
				
				if (hmf.templateMode)
				{
					HeroMaker.heroNameSizeTemplate = hm.heroNameSize;
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hm.card.changed = true;
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(setHeroNameFont))
		{
			JFontChooser fontChooser = new JFontChooser();
			int outcome = fontChooser.showDialog(hmf);
			if (outcome == JFontChooser.OK_OPTION)
			{
				hm.heroNameSize = fontChooser.getSelectedFontSize();
				hm.card.heroNameSize = fontChooser.getSelectedFontSize();
				
				hm.heroNameFontName = fontChooser.getSelectedFont().getFontName();
				hm.heroNameFontStyle = fontChooser.getSelectedFont().getStyle();
				
				if (hmf.templateMode)
				{
					HeroMaker.heroNameFontNameTemplate = hm.heroNameFontName;
					HeroMaker.heroNameFontStyleTemplate = hm.heroNameFontStyle;
					HeroMaker.heroNameSizeTemplate = fontChooser.getSelectedFontSize();
				}
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCostSize))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Hero Name Size", hm.costSize);
			if (s == null) { s = "" + hm.costSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.costSize; }
			try
			{
				hm.costSize = Integer.parseInt(s);
				
				if (hmf.templateMode)
				{
					HeroMaker.costSizeTemplate = hm.costSize;
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hm.card.changed = true;
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(setCostFont))
		{
			JFontChooser fontChooser = new JFontChooser();
			int outcome = fontChooser.showDialog(hmf);
			if (outcome == JFontChooser.OK_OPTION)
			{
				hm.costSize = fontChooser.getSelectedFontSize();
				
				hm.costFontName = fontChooser.getSelectedFont().getFontName();
				hm.costFontStyle = fontChooser.getSelectedFont().getStyle();
				
				if (hmf.templateMode)
				{
					HeroMaker.costFontNameTemplate = hm.costFontName;
					HeroMaker.costFontStyleTemplate = hm.costFontStyle;
					HeroMaker.costSizeTemplate = fontChooser.getSelectedFontSize();
				}
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCostColour))
		{
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(hmf, "Select Cost colour...", hm.costColor);
			if (outcome != null)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					HeroMaker.costColorTemplate = outcome;
					hm.costColor = outcome;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(setAttackSize))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Hero Name Size", hm.attackSize);
			if (s == null) { s = "" + hm.attackSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.attackSize; }
			try
			{
				hm.attackSize = Integer.parseInt(s);
				
				if (hmf.templateMode)
				{
					HeroMaker.attackSizeTemplate = hm.attackSize;
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hm.card.changed = true;
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(setAttackFont))
		{
			JFontChooser fontChooser = new JFontChooser();
			int outcome = fontChooser.showDialog(hmf);
			if (outcome == JFontChooser.OK_OPTION)
			{
				hm.attackSize = fontChooser.getSelectedFontSize();
				
				hm.attackFontName = fontChooser.getSelectedFont().getFontName();
				hm.attackFontStyle = fontChooser.getSelectedFont().getStyle();
				
				if (hmf.templateMode)
				{
					HeroMaker.attackFontNameTemplate = hm.attackFontName;
					HeroMaker.attackFontStyleTemplate = hm.attackFontStyle;
					HeroMaker.attackSizeTemplate = fontChooser.getSelectedFontSize();
				}
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setAttackColour))
		{
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(hmf, "Select Attack colour...", hm.attackColor);
			if (outcome != null)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					HeroMaker.attackColorTemplate = outcome;
					hm.attackColor = outcome;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(setRecruitSize))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Hero Name Size", hm.recruitSize);
			if (s == null) { s = "" + hm.recruitSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.recruitSize; }
			try
			{
				hm.recruitSize = Integer.parseInt(s);
				
				if (hmf.templateMode)
				{
					HeroMaker.recruitSizeTemplate = hm.recruitSize;
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hm.card.changed = true;
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(setRecruitFont))
		{
			JFontChooser fontChooser = new JFontChooser();
			int outcome = fontChooser.showDialog(hmf);
			if (outcome == JFontChooser.OK_OPTION)
			{
				hm.recruitSize = fontChooser.getSelectedFontSize();
				
				hm.recruitFontName = fontChooser.getSelectedFont().getFontName();
				hm.recruitFontStyle = fontChooser.getSelectedFont().getStyle();
				
				if (hmf.templateMode)
				{
					HeroMaker.recruitFontNameTemplate = hm.recruitFontName;
					HeroMaker.recruitFontStyleTemplate = hm.recruitFontStyle;
					HeroMaker.recruitSizeTemplate = fontChooser.getSelectedFontSize();
				}
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setRecruitColour))
		{
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(hmf, "Select Recruit colour...", hm.recruitColor);
			if (outcome != null)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					HeroMaker.recruitColorTemplate = outcome;
					hm.recruitColor = outcome;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(close))
		{
			hmf.setVisible(false);
		}
		
		if (e.getSource().equals(setBackgroundImage))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Image files", "png", "jpeg", "jpg", "bmp");
		    chooser.addChoosableFileFilter(filter1);
		    chooser.setFileFilter(filter1);
			int outcome = chooser.showOpenDialog(hmf);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					//ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
					//hmf.imageLabel.setIcon(ii);
					hm.card.imagePath = chooser.getSelectedFile().getAbsolutePath();
					hm.card.imageZoom = 1.0d;
					hm.card.imageOffsetX = 0;
					hm.card.imageOffsetY = 0;
					
					hmf.reRenderCard();
					hm.card.changed = true;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		
		if (e.getSource().equals(setBackgroundZoom))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Background Zoom", hm.card.imageZoom);
			if (s == null) { s = "" + hm.card.imageZoom; }
			if (s != null && s.isEmpty()) { s = "" + hm.card.imageZoom; }
			try
			{
				hm.card.imageZoom = Double.parseDouble(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hm.card.changed = true;
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(resetTemplate))
		{
			HeroMaker.resetTemplateValues();
			hm.resetTemplateValuesInstance();
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(setCardNameColour))
		{
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(hmf, "Select card name colour...", hm.cardNameColor);
			if (outcome != null)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					HeroMaker.cardNameColorTemplate = outcome;
					hm.cardNameColor = outcome;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(setHeroNameColour))
		{
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(hmf, "Select hero name colour...", hm.heroNameColor);
			if (outcome != null)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					HeroMaker.heroNameColorTemplate = outcome;
					hm.heroNameColor = outcome;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(setAbilityTextColour))
		{
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(hmf, "Select hero name colour...", hm.textColor);
			if (outcome != null)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					HeroMaker.abilityTextColorTemplate = outcome;
					hm.textColor = outcome;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(setTeamPowerColour))
		{
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(hmf, "Select Team/Power underlay colour...", hm.teamPowerUnderlayColor);
			if (outcome != null)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					HeroMaker.teamPowerUnderlayColorTemplate = outcome;
					hm.teamPowerUnderlayColor = outcome;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(setTeamPowerRadius))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Ability Text Size", hm.teamBlurRadius);
			if (s == null) { s = "" + hm.teamBlurRadius; }
			if (s != null && s.isEmpty()) { s = "" + hm.teamBlurRadius; }
			try
			{
				hm.teamBlurRadius = Integer.parseInt(s);
				hm.powerBlurRadius = Integer.parseInt(s);
				
				if (hmf.templateMode)
				{
					HeroMaker.teamPowerBlurRadiusTemplate = hm.teamBlurRadius;
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(highlightBlur))
		{
			highlightBlur.setSelected(true);
			highlightBanner.setSelected(false);
			HeroMaker.nameHighlightTemplate = "Blur";
			hm.nameHighlight = "Blur";
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hm.card.changed = true;
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(highlightBanner))
		{
			highlightBlur.setSelected(false);
			highlightBanner.setSelected(true);
			HeroMaker.nameHighlightTemplate = "Banner";
			hm.nameHighlight = "Banner";
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hm.card.changed = true;
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(setNumberInDeck))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Number in Deck", hm.card.numberInDeck);
			if (s == null) { s = "" + hm.card.numberInDeck; }
			if (s != null && s.isEmpty()) { s = "" + hm.card.numberInDeck; }
			try
			{
				hm.card.numberInDeck = Integer.parseInt(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(dualStyleHalfAndHalf))
		{
			dualStyleHalfAndHalf.setSelected(true);
			dualStyleSideBySide.setSelected(false);
			dualStyleTopAndBottom.setSelected(false);
			HeroMaker.dualClassStyle = DUAL_CLASS_STYLE.HALF_AND_HALF;
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			try
			{	
				hmf.reRenderCard();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(dualStyleSideBySide))
		{
			dualStyleHalfAndHalf.setSelected(false);
			dualStyleSideBySide.setSelected(true);
			dualStyleTopAndBottom.setSelected(false);
			HeroMaker.dualClassStyle = DUAL_CLASS_STYLE.SIDE_BY_SIDE;
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			try
			{	
				hmf.reRenderCard();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(dualStyleTopAndBottom))
		{
			dualStyleHalfAndHalf.setSelected(false);
			dualStyleSideBySide.setSelected(false);
			dualStyleTopAndBottom.setSelected(true);
			HeroMaker.dualClassStyle = DUAL_CLASS_STYLE.TOP_AND_BOTTOM;
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			try
			{	
				hmf.reRenderCard();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(multiClass))
		{
			if (!multiClass.isSelected())
			{
				hm.card.cardPower2 = null;
			}
			else
			{
				hm.card.cardPower2 = Icon.valueOf("INSTINCT");
			}
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			try
			{	
				hmf.reRenderCard();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	public HeroMakerToolbar getHeroMakerToolbar()
	{
		return tb;
	}
}
