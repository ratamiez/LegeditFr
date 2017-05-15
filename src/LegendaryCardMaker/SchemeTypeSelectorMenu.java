package LegendaryCardMaker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import LegendaryCardMaker.LegendarySchemeMaker.SchemeCardType;

public class SchemeTypeSelectorMenu extends JMenu implements ActionListener{
	
	JMenuItem newSchemeType = new JMenuItem(Messages.getString("Toolbar.NewSchemeType"));
	JMenuItem rename = new JMenuItem(Messages.getString("Toolbar.RenameSchemeType"));
	JMenuItem headingColour = new JMenuItem(Messages.getString("Toolbar.ChangeHeadingColour"));
	JMenuItem customHeadings = new JMenuItem(Messages.getString("Toolbar.ChangeCustomHeadings"));
	JMenuItem delete = new JMenuItem(Messages.getString("Toolbar.DeleteSchemeType"));
	
	public LegendaryCardMakerFrame lcmf;
	
	static CardMakerToolbar tb = null;
	
	public SchemeTypeSelectorMenu(LegendaryCardMakerFrame lcmf, CardMakerToolbar tb)
	{
		this.tb = tb;
		this.lcmf = lcmf;
		
		this.setText(Messages.getString("Toolbar.Edit"));
		
		newSchemeType.addActionListener(this);
		add(newSchemeType);
		
		addSeparator();
		
		rename.addActionListener(this);
		add(rename);
		
		headingColour.addActionListener(this);
		add(headingColour);
		
		customHeadings.addActionListener(this);
		add(customHeadings);
		
		delete.addActionListener(this);
		add(delete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(newSchemeType))
		{
			String name = JOptionPane.showInputDialog(LegendaryCardMakerFrame.lcmf,Messages.getString("IHM.EnterSchemeTypeName"), null);
			if (name == null) { return; }
			if (name != null && name.isEmpty()) { return; }
			
			JColorChooser chooser = new JColorChooser();
			Color bgColor = chooser.showDialog(LegendaryCardMakerFrame.lcmf, Messages.getString("IHM.SelectHeadingColour"), null);
			if (bgColor == null)
			{
				return;
			}
			
			boolean headings = false;
			int outcome = JOptionPane.showConfirmDialog(LegendaryCardMakerFrame.lcmf, Messages.getString("IHM.CustomHeadings"), Messages.getString("Toolbar.CustomHeadings"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (outcome == JOptionPane.YES_OPTION)
			{
				headings = true;
			}
			else
			{
				headings = false;
			}
			
			SchemeCardType type = new SchemeCardType(bgColor, name.toUpperCase(), headings);
			SchemeCardType.values().add(type);
			
			getSchemeTypeListModel().addElement(type);
			
			SchemeCardType.saveSchemeTypeDefinitions();
		}
		
		if (e.getSource().equals(rename))
		{
			if (getCurrentSchemeType() == null)
			{
				return;
			}
			
			String name = JOptionPane.showInputDialog(lcmf, Messages.getString("IHM.EnterSchemeTypeName"), getCurrentSchemeType().getEnumName());
			if (name == null) { return; }
			if (name != null && name.isEmpty()) { return; }
			
			getCurrentSchemeType().setDisplayString(name);
			
			SchemeCardType.saveSchemeTypeDefinitions();
		}
		
		if (e.getSource().equals(headingColour))
		{
			if (getCurrentSchemeType() == null)
			{
				return;
			}
			
			JColorChooser chooser = new JColorChooser();
			Color outcome = chooser.showDialog(lcmf, Messages.getString("IHM.SelectHeadingColour"), getCurrentSchemeType().getBgColor());
			if (outcome != null)
			{
				getCurrentSchemeType().setBgColor(outcome);
			}
			
			SchemeCardType.saveSchemeTypeDefinitions();
			
		}
		
		if (e.getSource().equals(customHeadings))
		{
			if (getCurrentSchemeType() == null)
			{
				return;
			}
			
			boolean headings = false;
			int outcome = JOptionPane.showConfirmDialog(this, Messages.getString("IHM.CustomHeadings"),Messages.getString("IHM.CustomHeadings"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (outcome == JOptionPane.YES_OPTION)
			{
				headings = true;
			}
			else
			{
				headings = false;
			}
			
			getCurrentSchemeType().setAllowHeadings(headings);
			
			SchemeCardType.saveSchemeTypeDefinitions();
		}
		
		if (e.getSource().equals(delete))
		{
			if (getCurrentSchemeType() == null)
			{
				return;
			}
			
			int outcome = JOptionPane.showOptionDialog(lcmf, Messages.getString("IHM.DeleteSchemeType"), Messages.getString("IHM.DeleteSchemeType"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (outcome == JOptionPane.YES_OPTION)
			{
				SchemeCardType.values().remove(getCurrentSchemeType());
				getSchemeTypeListModel().removeElement(getCurrentSchemeType());
				
				SchemeCardType.saveSchemeTypeDefinitions();
			}
		}
	}
	
	public JList getSchemeTypeList()
	{
		return lcmf.schemeTypeList;
	}
	
	public DefaultListModel getSchemeTypeListModel()
	{
		return lcmf.schemeTypeListModel;
	}
	
	public SchemeCardType getCurrentSchemeType()
	{
		SchemeCardType i = null;
		
		if (lcmf.schemeTypeListModel.size() > 0 && lcmf.schemeTypeList.getSelectedIndex() >= 0)
		{
			i = (SchemeCardType)lcmf.schemeTypeListModel.get(lcmf.schemeTypeList.getSelectedIndex());
		}
		
		return i;
	}
}
