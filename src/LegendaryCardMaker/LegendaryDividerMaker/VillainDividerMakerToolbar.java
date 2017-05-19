package LegendaryCardMaker.LegendaryDividerMaker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import LegendaryCardMaker.Icon;
import LegendaryCardMaker.LegendaryCardMakerFrame;
import LegendaryCardMaker.Messages;

public class VillainDividerMakerToolbar extends JMenuBar implements ActionListener{

	VillainDividerMaker hm;
	VillainDividerMakerFrame hmf;
	
	JMenu file = new JMenu(Messages.getString("Toolbar.File"));
	
	JMenuItem exportJPG = new JMenuItem(Messages.getString("Toolbar.ExportJPEG"));
	JMenuItem exportPNG = new JMenuItem(Messages.getString("Toolbar.ExportPNG"));
	JMenuItem exportPrinterStudioPNG = new JMenuItem(Messages.getString("Toolbar.ExportPrinterStudioPNG"));
	JMenuItem close = new JMenuItem(Messages.getString("Toolbar.Close"));
	
	JMenu edit = new JMenu(Messages.getString("Toolbar.Edit"));
	
	JMenu dividerCardStyle = new JMenu(Messages.getString("Toolbar.IconStyle"));
	JCheckBoxMenuItem divCardNone = new JCheckBoxMenuItem(Messages.getString("Toolbar.None"));
	JCheckBoxMenuItem divCardPowerIcons = new JCheckBoxMenuItem(Messages.getString("Toolbar.PowerIcons"));
	
	JMenu dividerBodyStyle = new JMenu(Messages.getString("Toolbar.BodyStyle"));
	JCheckBoxMenuItem divStyleImages = new JCheckBoxMenuItem(Messages.getString("Toolbar.Images"));
	JCheckBoxMenuItem divStyleTeamLogo = new JCheckBoxMenuItem(Messages.getString("Toolbar.TeamLogo"));
	
	JMenu dividerIcon = new JMenu(Messages.getString("Card.Icon"));
	JMenu dividerIconPower = new JMenu(Messages.getString("Card.Powers"));
	JMenu dividerIconTeam = new JMenu(Messages.getString("Card.Teams"));
	JMenu dividerIconAttributes = new JMenu(Messages.getString("Card.Attributes"));
	
	JMenuItem setBackgroundImage = new JMenuItem(Messages.getString("Toolbar.SetBackgroundImage"));
	JMenuItem setBackgroundZoom = new JMenuItem(Messages.getString("Toolbar.SetBackgroundZoom"));
	
	JMenuItem setForegroundImage = new JMenuItem(Messages.getString("Toolbar.SetForegroundImage"));
	JMenuItem setForegroundZoom = new JMenuItem(Messages.getString("Toolbar.SetForegroundZoom"));
	
	JMenuItem resetImage = new JMenuItem(Messages.getString("Toolbar.ResetImage"));
	
	JCheckBoxMenuItem opaqueTitleBar = new JCheckBoxMenuItem(Messages.getString("Toolbar.OpaqueTitleBar"));
	JMenuItem opaqueTitleColour = new JMenuItem(Messages.getString("Toolbar.OpaqueTitleBarColour"));
	
	static VillainDividerMakerToolbar tb = null;
	
	public VillainDividerMakerToolbar(VillainDividerMaker hm, VillainDividerMakerFrame hmf)
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
		
		for (Icon icon : Icon.values())
		{
			if (icon.getIconType().equals(Icon.ICON_TYPE.NONE))
			{
				String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
				s = s.replace("_", " ");
				JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
				m.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
						Icon icon = Icon.valueOf(enumValue);
						tb.hmf.hm.villain.dividerIconEnum = enumValue;
						
						tb.hmf.reRenderCard();
						tb.hm.villain.changed = true;
					}
				});
				
				dividerIcon.add(m);
			}
			
			if (icon.getIconType().equals(Icon.ICON_TYPE.TEAM))
			{
				String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
				s = s.replace("_", " ");
				JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
				m.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
						Icon icon = Icon.valueOf(enumValue);
						tb.hmf.hm.villain.dividerIconEnum = enumValue;
						
						tb.hmf.reRenderCard();
						tb.hm.villain.changed = true;
					}
				});
				
				dividerIconTeam.add(m);
			}
			
			if (icon.getIconType().equals(Icon.ICON_TYPE.POWER))
			{
				String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
				s.replace("_", " ");
				JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
				m.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
						Icon icon = Icon.valueOf(enumValue);
						tb.hm.villain.dividerIconEnum = enumValue;
						
						tb.hmf.reRenderCard();
						tb.hm.villain.changed = true;
					}
				});
				
				dividerIconPower.add(m);
			}
			
			if (icon.getIconType().equals(Icon.ICON_TYPE.ATTRIBUTE))
			{
				String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
				s.replace("_", " ");
				JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
				m.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
						Icon icon = Icon.valueOf(enumValue);
						tb.hm.villain.dividerIconEnum = enumValue;
						
						tb.hmf.reRenderCard();
						tb.hm.villain.changed = true;
					}
				});
				
				dividerIconAttributes.add(m);
			}
			
			if (icon.getIconType().equals(Icon.ICON_TYPE.NONE))
			{
				String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
				s.replace("_", " ");
				JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
				m.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
						Icon icon = Icon.valueOf(enumValue);
						tb.hm.villain.dividerIconEnum = enumValue;
						
						tb.hmf.reRenderCard();
						tb.hm.villain.changed = true;
					}
				});
				
				dividerIconAttributes.add(m);
			}
		}
		dividerIcon.add(dividerIconTeam);
		dividerIcon.add(dividerIconPower);
		dividerIcon.add(dividerIconAttributes);
		
		if (hmf.templateMode)
		{
			
			divCardNone.addActionListener(this);
			dividerCardStyle.add(divCardNone);
			divCardPowerIcons.addActionListener(this);
			dividerCardStyle.add(divCardPowerIcons);
			edit.add(dividerCardStyle);
			setSelectedCardStyle();
			
			divStyleImages.addActionListener(this);
			dividerBodyStyle.add(divStyleImages);
			divStyleTeamLogo.addActionListener(this);
			//dividerBodyStyle.add(divStyleTeamLogo);
			edit.add(dividerBodyStyle);
			setSelectedBodyStyle();
			
			edit.addSeparator();
			
			setBackgroundImage.addActionListener(this);
			edit.add(setBackgroundImage);
		
			setBackgroundZoom.addActionListener(this);
			edit.add(setBackgroundZoom);
			
			resetImage.addActionListener(this);
			edit.add(resetImage);
			
			edit.addSeparator();
			
			opaqueTitleBar.addActionListener(this);
			opaqueTitleBar.setSelected(LegendaryCardMakerFrame.lcmf.lcm.dividerTitleBarVisible);
			edit.add(opaqueTitleBar);
			
			opaqueTitleColour.addActionListener(this);
			edit.add(opaqueTitleColour);
		}
		else
		{
			setForegroundImage.addActionListener(this);
			edit.add(setForegroundImage);
			
			setForegroundZoom.addActionListener(this);
			edit.add(setForegroundZoom);
			
			resetImage.addActionListener(this);
			edit.add(resetImage);
			
			//edit.addSeparator();
			
			//edit.add(dividerIcon);
		}
		
		add(edit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(exportJPG))
		{
			JFileChooser chooser = new JFileChooser();
			int outcome = chooser.showSaveDialog(this);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				BufferedImage bi = hm.generateDivider();
				try
				{
					hm.exportToJPEG(bi, chooser.getSelectedFile());
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(exportPNG))
		{
			JFileChooser chooser = new JFileChooser();
			int outcome = chooser.showSaveDialog(this);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				BufferedImage bi = hm.generateDivider();
				try
				{
					hm.exportToPNG(bi, chooser.getSelectedFile());
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		
		
		if (e.getSource().equals(close))
		{
			hmf.setVisible(false);
		}
		
		if (e.getSource().equals(setForegroundImage))
		{
			JFileChooser chooser = new JFileChooser();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			int outcome = chooser.showOpenDialog(hmf);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				
				try
				{
					//ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
					//hmf.imageLabel.setIcon(ii);
					hm.villain.imagePath = chooser.getSelectedFile().getAbsolutePath();
					hm.villain.imageZoom = 1.0d;
					hm.villain.imageOffsetX = 0;
					hm.villain.imageOffsetY = 0;
					
					hmf.reRenderCard();
					hm.villain.changed = true;
					
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(setForegroundZoom))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterBackgroundZoom"), hm.villain.imageZoom);
			if (s == null) { s = "" + hm.villain.imageZoom; }
			if (s != null && s.isEmpty()) { s = "" + hm.villain.imageZoom; }
			try
			{
				hm.villain.imageZoom = Double.parseDouble(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			hm.villain.changed = true;
		}
		
		if (e.getSource().equals(setBackgroundImage))
		{
			JFileChooser chooser = new JFileChooser();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			int outcome = chooser.showOpenDialog(hmf);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				
				try
				{
					//ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
					//hmf.imageLabel.setIcon(ii);
					LegendaryCardMakerFrame.lcmf.lcm.dbImagePath = chooser.getSelectedFile().getAbsolutePath();
					LegendaryCardMakerFrame.lcmf.lcm.dbImageZoom = 1.0d;
					LegendaryCardMakerFrame.lcmf.lcm.dbImageOffsetX = 0;
					LegendaryCardMakerFrame.lcmf.lcm.dbImageOffsetY = 0;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(setBackgroundZoom))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterBackgroundZoom"), LegendaryCardMakerFrame.lcmf.lcm.dbImageZoom);
			if (s == null) { s = "" + LegendaryCardMakerFrame.lcmf.lcm.dbImageZoom; }
			if (s != null && s.isEmpty()) { s = "" + LegendaryCardMakerFrame.lcmf.lcm.dbImageZoom; }
			try
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				LegendaryCardMakerFrame.lcmf.lcm.dbImageZoom = Double.parseDouble(s);
				hmf.reRenderCard();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(opaqueTitleBar))
		{
			LegendaryCardMakerFrame.lcmf.lcm.dividerTitleBarVisible = opaqueTitleBar.isSelected();
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(opaqueTitleColour))
		{
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(hmf, Messages.getString("IHM.SelectTitleBarColour"), LegendaryCardMakerFrame.lcmf.lcm.dividerTitleBarColour);
			if (outcome != null)
			{
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					LegendaryCardMakerFrame.lcmf.lcm.dividerTitleBarColour = outcome;
					
					hmf.reRenderCard();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		if (e.getSource().equals(resetImage))
		{
			if (hmf.templateMode)
			{
				LegendaryCardMakerFrame.lcmf.lcm.dbImageOffsetX = 0;
				LegendaryCardMakerFrame.lcmf.lcm.dbImageOffsetY = 0;
				LegendaryCardMakerFrame.lcmf.lcm.dbImagePath = null;
				LegendaryCardMakerFrame.lcmf.lcm.dbImageZoom = 1.0d;
			}
			else
			{
				hm.villain.imageOffsetX = 0;
				hm.villain.imageOffsetY = 0;
				hm.villain.imagePath = null;
				hm.villain.imageZoom = 1.0d;
			}
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(divCardNone) || e.getSource().equals(divCardPowerIcons))
		{
			JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
			LegendaryCardMakerFrame.lcmf.lcm.dividerCardStyle = item.getText().replace(" ", "");
			
			setSelectedCardStyle();
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		if (e.getSource().equals(divStyleImages) || e.getSource().equals(divStyleTeamLogo))
		{
			JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
			LegendaryCardMakerFrame.lcmf.lcm.dividerBodyStyle = item.getText().replace(" ", "");
			
			setSelectedBodyStyle();
			
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			hmf.reRenderCard();
			hmf.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

	}

	public VillainDividerMakerToolbar getHeroMakerToolbar()
	{
		return tb;
	}
	
	public void setSelectedCardStyle()
	{
		boolean found = false;
		for (Component c : dividerCardStyle.getMenuComponents())
		{
			if (c instanceof JCheckBoxMenuItem)
			{
				JCheckBoxMenuItem item = (JCheckBoxMenuItem)c;
				if (item.getText().replace(" ", "").equals(LegendaryCardMakerFrame.lcmf.lcm.dividerCardStyle))
				{
					item.setSelected(true);
					found = true;
				}
				else
				{
					item.setSelected(false);
				}
			}
		}
		if (!found)
		{
			divCardPowerIcons.setSelected(true);
		}
	}
	
	public void setSelectedBodyStyle()
	{
		boolean found = false;
		for (Component c : dividerBodyStyle.getMenuComponents())
		{
			if (c instanceof JCheckBoxMenuItem)
			{
				JCheckBoxMenuItem item = (JCheckBoxMenuItem)c;
				if (item.getText().replace(" ", "").equals(LegendaryCardMakerFrame.lcmf.lcm.dividerBodyStyle))
				{
					item.setSelected(true);
					found = true;
				}
				else
				{
					item.setSelected(false);
				}
			}
		}
		if (!found)
		{
			divStyleImages.setSelected(true);
		}
	}
}
