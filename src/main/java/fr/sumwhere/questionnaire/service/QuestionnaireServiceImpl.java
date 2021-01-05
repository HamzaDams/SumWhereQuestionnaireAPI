package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.model.Questionnaire;
import fr.sumwhere.questionnaire.repo.QuestionnaireRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireServiceImpl.class);
    @Autowired
    private QuestionnaireRepo questionnaireRepo;



    @Override
    public boolean envoyerEmail(String to, String sujet, String texte, String email, String adresse, String nom, String prenom, String site, float latitude, float longitude) {

        boolean isEnvoyer=false;

        String host="smtp.gmail.com";

        //Recup system props
        Properties props = System.getProperties();
        System.out.println("PROPS" + props);

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.auth","true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("test31.compte31@gmail.com", "Motdepasse123!");
            }
        });
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        String coords = latitude + "," + longitude;
        String urlMap = "http://maps.google.com/maps/api/staticmap?center=" + coords + ",&zoom=15&markers=" + coords + "|" + coords + "&path=color:0x0000FF80|weight:5|" + coords + "&size=460x460&key=AIzaSyD-25Q3gSx-vVlsmdfXtgEGc37bqwmwKjo";
        String result = "<h1> Message reçu </h1>" + "" +
                " <html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\"><head>\n" +
                "\t<!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]-->\n" +
                "\t<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\">\n" +
                "\t<meta content=\"width=device-width\" name=\"viewport\">\n" +
                "\t<!--[if !mso]><!-->\n" +
                "\t<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\">\n" +
                "\t<!--<![endif]-->\n" +
                "\t<title></title>\n" +
                "\t<!--[if !mso]><!-->\n" +
                "\t<link href=\"https://fonts.googleapis.com/css?family=Roboto\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\t<link href=\"https://fonts.googleapis.com/css?family=Ubuntu\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\t<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\t<link href=\"https://fonts.googleapis.com/css?family=Droid+Serif\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\t<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\t<!--<![endif]-->\n" +
                "\t<style type=\"text/css\">\n" +
                "\t\tbody {\n" +
                "\t\t\tmargin: 0;\n" +
                "\t\t\tpadding: 0;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ttable,\n" +
                "\t\ttd,\n" +
                "\t\ttr {\n" +
                "\t\t\tvertical-align: top;\n" +
                "\t\t\tborder-collapse: collapse;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t* {\n" +
                "\t\t\tline-height: inherit;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ta[x-apple-data-detectors=true] {\n" +
                "\t\t\tcolor: inherit !important;\n" +
                "\t\t\ttext-decoration: none !important;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "\t<style id=\"media-query\" type=\"text/css\">\n" +
                "\t\t@media (max-width: 500px) {\n" +
                "\n" +
                "\t\t\t.block-grid,\n" +
                "\t\t\t.col {\n" +
                "\t\t\t\tmin-width: 320px !important;\n" +
                "\t\t\t\tmax-width: 100% !important;\n" +
                "\t\t\t\tdisplay: block !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.block-grid {\n" +
                "\t\t\t\twidth: 100% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.col {\n" +
                "\t\t\t\twidth: 100% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.col_cont {\n" +
                "\t\t\t\tmargin: 0 auto;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\timg.fullwidth,\n" +
                "\t\t\timg.fullwidthOnMobile {\n" +
                "\t\t\t\tmax-width: 100% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col {\n" +
                "\t\t\t\tmin-width: 0 !important;\n" +
                "\t\t\t\tdisplay: table-cell !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack.two-up .col {\n" +
                "\t\t\t\twidth: 50% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num2 {\n" +
                "\t\t\t\twidth: 16.6% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num3 {\n" +
                "\t\t\t\twidth: 25% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num4 {\n" +
                "\t\t\t\twidth: 33% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num5 {\n" +
                "\t\t\t\twidth: 41.6% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num6 {\n" +
                "\t\t\t\twidth: 50% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num7 {\n" +
                "\t\t\t\twidth: 58.3% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num8 {\n" +
                "\t\t\t\twidth: 66.6% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num9 {\n" +
                "\t\t\t\twidth: 75% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.no-stack .col.num10 {\n" +
                "\t\t\t\twidth: 83.3% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.video-block {\n" +
                "\t\t\t\tmax-width: none !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.mobile_hide {\n" +
                "\t\t\t\tmin-height: 0px;\n" +
                "\t\t\t\tmax-height: 0px;\n" +
                "\t\t\t\tmax-width: 0px;\n" +
                "\t\t\t\tdisplay: none;\n" +
                "\t\t\t\toverflow: hidden;\n" +
                "\t\t\t\tfont-size: 0px;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.desktop_hide {\n" +
                "\t\t\t\tdisplay: block !important;\n" +
                "\t\t\t\tmax-height: none !important;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "\t<style id=\"icon-media-query\" type=\"text/css\">\n" +
                "\t\t@media (max-width: 500px) {\n" +
                "\t\t\t.icons-inner {\n" +
                "\t\t\t\ttext-align: center;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.icons-inner td {\n" +
                "\t\t\t\tmargin: 0 auto;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "\n" +
                "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #FFFFFF;\">\n" +
                "\t<!--[if IE]><div class=\"ie-browser\"><![endif]-->\n" +
                "\t<table bgcolor=\"#FFFFFF\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t<tbody>\n" +
                "\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color:#FFFFFF\"><![endif]-->\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\"background-color:transparent;width:480px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num12\" style=\"min-width: 320px; max-width: 480px; display: table-cell; vertical-align: top; width: 480px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div align=\"center\" class=\"img-container center autowidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\"><![endif]--><img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center autowidth\" src=\"https://drive.google.com/uc?export=download&id=1XB6aTJTmpW11q0zxOz22dGVGsvuBBO8S\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 400px; display: block;\" title=\"Valdoise\" width=\"400\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: 'Trebuchet MS', Tahoma, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"line-height: 1.2; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #555555; mso-line-height-alt: 14px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"font-size: 14px; line-height: 1.2; text-align: center; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<strong><span style=\"font-size: 20px; color: #e03326;\">Candidature de\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tréférencement d'un producteur local</span></strong></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid two-up\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"240\" style=\"background-color:transparent;width:240px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 240px; width: 240px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\"><tr><td style=\"padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"\" style=\"height:31.5pt; width:195pt; v-text-anchor:middle;\" arcsize=\"10%\" stroke=\"false\" fillcolor=\"#3AAEE0\"><w:anchorlock/><v:textbox inset=\"0,0,0,0\"><center style=\"color:#ffffff; font-family:Arial, sans-serif; font-size:16px\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"text-decoration:none;display:block;color:#ffffff;background-color:#3AAEE0;border-radius:4px;-webkit-border-radius:4px;-moz-border-radius:4px;width:100%; width:calc(100% - 2px);;border-top:1px solid #3AAEE0;border-right:1px solid #3AAEE0;border-bottom:1px solid #3AAEE0;border-left:1px solid #3AAEE0;padding-top:5px;padding-bottom:5px;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;text-align:center;mso-border-alt:none;word-break:keep-all;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;\"><strong><span data-mce-style=\"font-size: 16px; line-height: 32px;\" style=\"font-size: 16px; line-height: 32px;\">Valider</span></strong></span>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></center></v:textbox></v:roundrect></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td><td align=\"center\" width=\"240\" style=\"background-color:transparent;width:240px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 240px; width: 240px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\"><tr><td style=\"padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"\" style=\"height:31.5pt; width:178.5pt; v-text-anchor:middle;\" arcsize=\"10%\" stroke=\"false\" fillcolor=\"#878787\"><w:anchorlock/><v:textbox inset=\"0,0,0,0\"><center style=\"color:#ffffff; font-family:Arial, sans-serif; font-size:16px\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"text-decoration:none;display:block;color:#ffffff;background-color:#878787;border-radius:4px;-webkit-border-radius:4px;-moz-border-radius:4px;width:90%; width:calc(90% - 2px);;border-top:1px solid #878787;border-right:1px solid #878787;border-bottom:1px solid #878787;border-left:1px solid #878787;padding-top:5px;padding-bottom:5px;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;text-align:center;mso-border-alt:none;word-break:keep-all;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;\"><span style=\"font-size: 16px; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>Refuser</strong></span></span>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></center></v:textbox></v:roundrect></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\"background-color:transparent;width:480px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num12\" style=\"min-width: 320px; max-width: 480px; display: table-cell; vertical-align: top; width: 480px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid mixed-two-up\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"120\" style=\"background-color:transparent;width:120px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 120px; width: 120px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"word-break: break-word; vertical-align: top; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 5px;\" valign=\"top\"><img align=\"center\" class=\"icon\" height=\"32\" src=\"https://drive.google.com/uc?export=download&id=1wC-X9-8noMVhAy6QVa6w3zkB0SUGzmSa\" style=\"border:0;\" width=\"null\"></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td><td align=\"center\" width=\"360\" style=\"background-color:transparent;width:360px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num9\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 360px; width: 360px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<strong>Candidat</strong>:" + nom + " " + prenom + "</p>\n"+
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid mixed-two-up\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"120\" style=\"background-color:transparent;width:120px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 120px; width: 120px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"word-break: break-word; vertical-align: top; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 5px;\" valign=\"top\"><img align=\"center\" class=\"icon\" height=\"32\" src=\"https://drive.google.com/uc?export=download&id=1Jn1bsxDN071rpeZ9Bd5FDzh20b2xpTyp\" style=\"border:0;\" width=\"null\"></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td><td align=\"center\" width=\"360\" style=\"background-color:transparent;width:360px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num9\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 360px; width: 360px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<strong>Adresse</strong>: " + adresse + " " + " </p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid mixed-two-up\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"120\" style=\"background-color:transparent;width:120px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 120px; width: 120px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"word-break: break-word; vertical-align: top; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 5px;\" valign=\"top\"><img align=\"center\" class=\"icon\" height=\"32\" src=\"https://drive.google.com/uc?export=download&id=1LiRfy8jGdmWGqpqZjaHmJJceRAa6P4Bf\" style=\"border:0;\" width=\"null\"></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td><td align=\"center\" width=\"360\" style=\"background-color:transparent;width:360px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num9\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 360px; width: 360px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<strong>Site Web</strong>: " + " " + site + "</p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid mixed-two-up\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"120\" style=\"background-color:transparent;width:120px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 120px; width: 120px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"word-break: break-word; vertical-align: top; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 5px;\" valign=\"top\"><img align=\"center\" class=\"icon\" height=\"32\" src=\"https://drive.google.com/uc?export=download&id=1oa3_daehM_1oiJwgqQugKKatI0frsRz0\" style=\"border:0;\" width=\"null\"></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td><td align=\"center\" width=\"360\" style=\"background-color:transparent;width:360px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num9\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 360px; width: 360px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"font-size: 14px;\"><strong>Nom de\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tl'exploitation:</strong> Ferme du lol</span></div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid mixed-two-up\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"120\" style=\"background-color:transparent;width:120px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 120px; width: 120px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"word-break: break-word; vertical-align: top; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 5px;\" valign=\"top\"><img align=\"center\" class=\"icon\" height=\"32\" src=\"https://drive.google.com/uc?export=download&id=128fv1J08uKxKEvMwb0o_ipVAP0SiUW6x\" style=\"border:0;\" width=\"null\"></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td><td align=\"center\" width=\"360\" style=\"background-color:transparent;width:360px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num9\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 360px; width: 360px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"font-size: 14px;\"><strong>Description de\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tl'activité:</strong> "+ texte +"</span></div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div style=\"background-color:transparent;\">\n" +
                "\t\t\t\t\t\t<div class=\"block-grid\" style=\"min-width: 320px; max-width: 480px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                "\t\t\t\t\t\t\t<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:480px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\"background-color:transparent;width:480px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<div class=\"col num12\" style=\"min-width: 320px; max-width: 480px; display: table-cell; vertical-align: top; width: 480px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\"><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#555555;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<strong>Localisation<br></strong></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div align=\"center\" class=\"img-container center autowidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\"><![endif]--><img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center autowidth\" src='"+ urlMap +"' style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 480px; display: block;\" title=\"Alternate text\" width=\"480\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--[if (!mso)&(!IE)]><!-->\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t<!--<![endif]-->\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t\t<!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</tbody>\n" +
                "\t</table>\n" +
                "\t<!--[if (IE)]></div><![endif]-->\n" +
                "\n" +
                "\n" +
                "</body></html> ";

        try {
            helper.setFrom("test31.compte31@gmail.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            helper.setSubject(sujet);
            //helper.setContent(result, "text/html");
            helper.setText(result, true);

            Transport.send(message);

            System.out.println(message);

            isEnvoyer=true;
        }catch(Exception e) {
            logger.error("L'envoi du questionnaire n'a pas pu être effectué" + e);
        }
        return isEnvoyer;
    }

    @Override
    public Questionnaire sauvegarderQuestionnaire(Questionnaire q) {
        Questionnaire qRecu = questionnaireRepo.save(q);
        return qRecu;
    }


}
