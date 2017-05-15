package LegendaryCardMaker.LegendarySchemeMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

import LegendaryCardMaker.CardTextDialog;
import LegendaryCardMaker.Icon;
import LegendaryCardMaker.Messages;

public class SchemeMakerToolbar extends JMenuBar implements ActionListener{

	SchemeMaker hm;
	SchemeMakerFrame hmf;
	
	JMenu file = new JMenu(Messages.getString("Toolbar.File"));
	
	JMenuItem exportJPG = new JMenuItem(Messages.getString("Toolbar.ExportJPEG"));
	JMenuItem exportPNG = new JMenuItem(Messages.getString("Toolbar.ExportPNG"));
	JMenuItem exportPrinterStudioPNG = new JMenuItem(Messages.getString("Toolbar.ExportPrinterStudioPNG"));
	JMenuItem close = new JMenuItem(Messages.getString("Toolbar.Close"));
	
	JMenu edit = new JMenu(Messages.getString("Toolbar.Edit"));
	
	JMenu cardTypes = new JMenu(Messages.getString("Toolbar.CardType"));
	List<JCheckBoxMenuItem> cardTypeItems = new ArrayList<JCheckBoxMenuItem>();
	
	JMenuItem setCardName = new JMenuItem(Messages.getString("Toolbar.SetCardName"));
	JMenuItem setCardNameSize = new JMenuItem(Messages.getString("Toolbar.SetCardNameSize"));
	JMenuItem setCardSubName = new JMenuItem(Messages.getString("Toolbar.SetCardSubName"));
	JMenuItem setCardSubNameSize = new JMenuItem(Messages.getString("Toolbar.SetCardSubNameSize"));
	JMenuItem setCardText = new JMenuItem(Messages.getString("Toolbar.SetCardText"));
	JMenuItem setCardTextSize = new JMenuItem(Messages.getString("Toolbar.SetCardTextSize"));
	JMenuItem setBackgroundImage = new JMenuItem(Messages.getString("Toolbar.SetBackgroundImage"));
	JMenuItem setBackgroundZoom = new JMenuItem(Messages.getString("Toolbar.SetBackgroundZoom"));
	JMenuItem setNumberInDeck = new JMenuItem(Messages.getString("Toolbar.SetNumberInDeck"));
	
	static SchemeMakerToolbar tb = null;
	
	public SchemeMakerToolbar(SchemeMaker hm, SchemeMakerFrame hmf)
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
		
		
		for (SchemeCardType type : SchemeCardType.values())
		{
			String s = type.toString().substring(0, 1).toUpperCase() + type.toString().substring(1).toLowerCase();
			s = s.replace("_", " ");
			JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
			m.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
					SchemeCardType icon = SchemeCardType.valueOf(enumValue);
					tb.hm.card.cardType = icon;
					
					for (JCheckBoxMenuItem item : tb.cardTypeItems)
					{
						if (item.getText()./*replace(" ", "_").*/toUpperCase().equals(enumValue))
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
			
			cardTypeItems.add(m);
			cardTypes.add(m);
		}
		
		for (JCheckBoxMenuItem item : cardTypeItems)
		{
			if (item.getText()./*replace(" ", "_").*/toUpperCase().equals(hm.card.cardType.toString()))
			{
				item.setSelected(true);
			}
			else
			{
				item.setSelected(false);
			}
		}
		edit.add(cardTypes);
				
		setCardName.addActionListener(this);
		edit.add(setCardName);
		
		setCardNameSize.addActionListener(this);
		edit.add(setCardNameSize);
		
		setCardSubName.addActionListener(this);
		edit.add(setCardSubName);
		
		setCardSubNameSize.addActionListener(this);
		edit.add(setCardSubNameSize);
		
		setCardText.addActionListener(this);
		edit.add(setCardText);
		
		setCardTextSize.addActionListener(this);
		edit.add(setCardTextSize);
		
		setBackgroundImage.addActionListener(this);
		edit.add(setBackgroundImage);
		
		setBackgroundZoom.addActionListener(this);
		edit.add(setBackgroundZoom);
		
		setNumberInDeck.addActionListener(this);
		edit.add(setNumberInDeck);
		
		add(edit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(exportJPG))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter(Messages.getString("IHM.JPEGFile"), "jpg", "jpeg");
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
						int confirm = JOptionPane.showConfirmDialog(hmf, Messages.getString("IHM.OverwriteFile"), Messages.getString("IHM.FileExists"), JOptionPane.YES_OPTION);
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
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter(Messages.getString("IHM.PNGFile"), "png");
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
						int confirm = JOptionPane.showConfirmDialog(hmf, Messages.getString("IHM.OverwriteFile"), Messages.getString("IHM.FileExists"), JOptionPane.YES_OPTION);
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
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter(Messages.getString("IHM.PNGFile"), "png");
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
						int confirm = JOptionPane.showConfirmDialog(hmf, Messages.getString("IHM.OverwriteFile"), Messages.getString("IHM.FileExists"), JOptionPane.YES_OPTION);
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
		
		if (e.getSource().equals(setCardText))
		{
			//String s = JOptionPane.showInputDialog(hmf, "Enter the Ability Text", hm.card.abilityText);
			CardTextDialog d = new CardTextDialog(hm.card.cardText);
			if (hm.card.cardType.doesAllowHeadings())
			{
				d.addHeaderIconButton();
				d.addHeadingButton();
			}
			String s = d.showInputDialog();
			if (s == null) { s = hm.card.cardText; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.cardText = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardName))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterCardName"), hm.card.name);
			if (s == null) { s = hm.card.name; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.name = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardSubName))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterCardSubName"), hm.card.subCategory);
			if (s == null) { s = hm.card.subCategory; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.subCategory = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardTextSize))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterAbilityTextSize"), hm.textSize);
			if (s == null) { s = "" + hm.textSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.textSize; }
			try
			{
				hm.textSize = Integer.parseInt(s);
				hm.card.cardTextSize = Integer.parseInt(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardNameSize))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterCardNameSize"), hm.cardNameSize);
			if (s == null) { s = "" + hm.cardNameSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.cardNameSize; }
			try
			{
				hm.cardNameSize = Integer.parseInt(s);
				hm.card.cardNameSize = Integer.parseInt(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardSubNameSize))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterCardSubNameSize"), hm.subCategorySize);
			if (s == null) { s = "" + hm.subCategorySize; }
			if (s != null && s.isEmpty()) { s = "" + hm.subCategorySize; }
			try
			{
				hm.subCategorySize = Integer.parseInt(s);
				hm.card.subCategorySize = Integer.parseInt(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(close))
		{
			hmf.setVisible(false);
		}
		
		if (e.getSource().equals(setBackgroundImage))
		{
			JFileChooser chooser = new JFileChooser();
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
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterBackgroundZoom"), hm.card.imageZoom);
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
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setNumberInDeck))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterNumberDeck"), hm.card.numberInDeck);
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
	}

	public SchemeMakerToolbar getSchemeMakerToolbar()
	{
		return tb;
	}
}
