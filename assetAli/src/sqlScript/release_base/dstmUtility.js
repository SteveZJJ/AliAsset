
var gPageChanged=false;var gPopupClicked=false;var gIgnoreAllChanges=false;var gProcessEventTimer;var gProcessEventSrcElemRef;var gProcessEventActionURL;var gProcessEventFieldValueString;var gMessageInitProcessing=false;var gCurrentFieldValues;var gCurrentFieldValuesDataIsland;var gLastFieldValues;var gLastFieldValuesDataIsland;var gProcessEventFoundStopError=false;var gProcessEventActionType;var gCurrentModuleTab=0;var gPageMessageButtonTemplate;var isSaveAction=false;var gAutoSave=true;var gScrollPending=false;var gErrorInMessages=false;var gWarningInMessages=false;var gInfoInMessages=false;var gMessageBarAutoHideTimer;var gMessageBarAutoHideDisabled=false;var gDDLBKeyDownProcessed=false;var gStylePrePath="../images/";var gFocusField=null;var gLastFocusField=null;var gFirstFocusField=null;var lastKeyPressedCode=-1;var gDestroyKeyCodeTimer=null;var gUserLoggingOut=false;var giMinYear=1900
var giMaxYear=2099
var gHelpWindow;var gLastExecutedParams=null;var gPerform7iFunctionFoundError=false;var gPerform7iFunctionFoundQuestion=false;var gLastGlasstopOn=null;var gQuestionInMessages=false;var globalLookupHelpObj=null;var globalLookupHelpTimer=null;var gSaveSubmitTimer=null;var gReportQueryString=null;var gNoResizePopup=false;var gCopyRecordStatus=null;var gObejectForCalCell=null;var gFokButton=null;var gProcessEventCoreResult=null;var gPreLoadMessageOnly=false;var gFVForCachedHTML=null;var gRefreshCachedHTMLData=false;var gCustomBodyOnloadTryTime=0;var gDefaultFieldsToValidate=null;var gMainEventsLoaded=false;if(typeof(window.gStyleCD)=="undefined"||window.gStyleCD==null)
{window.gStyleCD="default";}
var EG_DATE_FIELD="EG_DATE_FIELD";var gActiveMainIframe=null;var gLastMainIframe=null;var gForceSyncFV=false;var gDragField=null;var gSyncGridAUFlag=false;var gLastGridAUFRowIndx=null;var gSyncGridTemp=false;var gGridKeyFieldsToSelect=null;var gGridKeyValuesToSelect=null;var gWindowSrcElement=null;var gBypassUpdateParentWindow=false;function urlEncode(str)
{return encodeURIComponent(str);}
function urlDecode(str)
{return decodeURIComponent(str);}
function formOnkeypressFireBtn()
{try
{gFokButton.fireEvent("onclick");gFokButton=null;}
catch(e){}}
function formOnkeypress(pEnterButton,pEscapeButton)
{if(gDDLBKeyDownProcessed)
{gDDLBKeyDownProcessed=false;return;}
var vObject=null;var enterKeyPressed=(window.event.keyCode==13);if(enterKeyPressed){var quickFilterCheckBoxPressed=window.event.srcElement.id=="chkFilterValue";if(quickFilterCheckBoxPressed){return;}
if(isObject(pEnterButton)&&!isblank(pEnterButton))
vObject=document.getElementById(pEnterButton)
if(window.event.srcElement!==null)
{if(window.event.srcElement.type=="textarea")
{return;}}
if(vObject!==null)
{gFokButton=document.getElementById(pEnterButton);gFokButton.focus();try
{gCurrentFocusField.blur();killEvent();window.setTimeout("formOnkeypressFireBtn()",10);return false;}
catch(e){}
gFokButton.fireEvent("onclick");}}
if(window.event.keyCode==27)
{if(isObject(pEscapeButton)&&!isblank(pEscapeButton))
vObject=document.getElementById(pEscapeButton)
if(vObject!==null)
{gFokButton=document.getElementById(pEscapeButton);gFokButton.focus();try
{gCurrentFocusField.blur();killEvent();window.setTimeout("formOnkeypressFireBtn()",10);return false;}
catch(e){}
gFokButton.fireEvent("onclick");}}}
function bodyOnscroll()
{repositionDivOnBottom("messagebar")
if(window.custom_bodyonscroll)
{eval(window.custom_bodyonscroll)}}
function bodyOnresize()
{try
{if(document.getElementById('gridhead_table')!==null)
{gridsize();}
if(eval("window.mainWindow")!=null&&window.mainWindow)
{repositionDivOnBottom("messagebar")}
if(window.custom_bodyonresize)
{eval(window.custom_bodyonresize)}
openOrCloseDDLB(null,"close");}
catch(e)
{}}
function chkSize()
{try
{obj=window.event.srcElement
var textrange=obj.createTextRange();var text=textrange.text
if(text.length>obj.maxLength)
obj.value=text.substring(0,239)}
catch(e){}}
function isScreenDesignerMode(){return(getFieldValue("pagemode")=="editlayout");}
function bodyOnload(vFocusField)
{var ctxMenuHTML=document.getElementById("SubIframeBodyPopupMenu")
if(isObject(ctxMenuHTML)&&!isObject(oCtxMenu))
{oCtxMenu=new dstmContextMenu();}
var vTR;var i=0;try
{var win=getBrowserWindow();win.document.title=GSHORTPAGETITLE;}catch(e){}
if(typeof(g_main)!=="undefined")
{g_main.on_load();}
if(!(getFieldValue("pagemode")=="editlayout"))
{var saveBtn=getBrowserWindow().document.all('SAVE');if(window.mainWindow&&isObject(saveBtn))
{saveBtn.attachEvent("onmousedown",registerMouseEventSave);saveBtn.attachEvent("onclick",registerMouseEventSave);}
var clearBtn=document.all('clear');if(isObject(clearBtn))
{clearBtn.attachEvent("onmousedown",checkProcessEventCallFn);clearBtn.attachEvent("onmousedown",registerMouseEventClear);}
var submitBtn=document.all('submit');if(isObject(submitBtn))
{submitBtn.attachEvent("onmousedown",checkProcessEventCallFn);submitBtn.attachEvent("onmousedown",registerMouseEventSubmit);submitBtn.attachEvent("onclick",registerMouseEventSubmit);if(isObject(saveBtn))
{saveBtn.attachEvent("onmousedown",checkProcessEventCallFn);}}
else if(isObject(saveBtn))
{saveBtn.detachEvent("onmousedown",checkProcessEventCallFn);}}
else if(isObject(window.expandAllBlocksInDesigner()))
expandAllBlocksInDesigner();document.body.attachEvent("onclick",killLastKeyPressed);var vElements=document.getElementsByTagName('DIV')
for(i=0;i<vElements.length;i++)
{if(vElements[i].datatype=='listbox')
{markSelectedValuesLB(vElements[i]);removeEmptyNodeLB(vElements[i]);}}
var vElements=document.getElementsByTagName('input')
for(i=0;i<vElements.length;i++)
{vElements[i].attachEvent("onfocus",setGlobalCurrentFocusField);}
try
{callCustomBodyOnLoadAndSetOrg();}
catch(e)
{}
if(isObject(vFocusField))
setInitFieldFocus(vFocusField,vFocusField);var vWindow=getBrowserWindow();if(!isObject(eval("window.doNotHideStatusMt")))
{hideStatusMeter();vWindow.hideStatusMeter();}
if(getFieldValue("pagemode")=="editlayout"&&document.getElementById("LAYOUTMETA")!=null)
{screenDesignerAttachMouseEvents();window.document.body.attachEvent("onselectstart",killSelect);}
setFieldOnHyplerlinkedWindow();}
function callCustomBodyOnLoadAndSetOrg()
{var vFV=document.getElementById("FIELDVALUES");if(isObject(vFV)&&vFV.ondatasetcomplete!=null&&vFV.ondatasetcomplete!=""&&gCustomBodyOnloadTryTime<50)
{gCustomBodyOnloadTryTime++;setTimeout("callCustomBodyOnLoadAndSetOrg()",50);return;}
gCustomBodyOnloadTryTime=0;if(isObject(document.getElementById('gridhead_table')))
{reInitGrid();}
if(isObject(window.changeParentDisplayFields))
changeParentDisplayFields();if(isObject(window.custom_bodyonload))
custom_bodyonload();gMainEventsLoaded=true;if(isObject(eval("window.gOrganization")))
var orgField=window.gOrganization;try
{if(!isObject(orgField)||orgField=="")
orgField=getMainIframeWindow().gOrganization;}
catch(e){}
if(isObject(orgField))
{if(isObject(document.getElementById(orgField)))
{var vOrgValue=getFieldValue(orgField.toLowerCase());if(vOrgValue=="")
vOrgValue=getFieldValue(orgField.toUpperCase());document.getElementById(orgField).lastValidatedValue=vOrgValue;}}
try
{checkHeadrOnlyRecords();initWindow();}
catch(e){}
try
{custom_postInitWindow();}
catch(e){}
if(getHasDefaultValueFlag()=="true")
{window.setTimeout("handleDefaultValues()",5);}}
function setInitFieldFocus(vFocusField,vFirstFocusField)
{if(!isblank(vFirstFocusField))
gFirstFocusField=vFirstFocusField;else
gFirstFocusField=vFocusField;setFieldFocus(vFocusField,true);}
function setFieldFocus(vFocusField,syncGfieldFocus)
{var win=getBrowserWindow();if(isObject(win.mainWindow)&&win.mainWindow)
{if(win.messageBarOpen(""))
return;}
else
{if(messageBarOpen(""))
return;}
if(document.getElementById(vFocusField)!==null)
{var vTR=document.getElementById(vFocusField);var i=0;while(vTR.tagName!="TR")
{i++;if(i>10)
{break;}
vTR=vTR.parentElement;}
if(vTR.style.display!="none"&&document.getElementById(vFocusField).disabled==false)
{try
{if(isObject(syncGfieldFocus)&&syncGfieldFocus)
{gLastFocusField=gFocusField;gFocusField=vFocusField;}
if(document.getElementById(vFocusField).datatype=='select')
{document.getElementById(vFocusField+'_display').select();document.getElementById(vFocusField+'_display').focus();}
else if(document.getElementById(vFocusField).datatype=='time')
{document.getElementById(vFocusField+'_HH_display').select();document.getElementById(vFocusField+'_HH_display').focus();}
else if(document.getElementById(vFocusField).datatype=='timehour')
{document.getElementById(vFocusField+'_display').select();document.getElementById(vFocusField+'_display').focus();}
else if(document.getElementById(vFocusField).datatype=='timeminute')
{document.getElementById(vFocusField+'_display').select();document.getElementById(vFocusField+'_display').focus();}
else
{document.getElementById(vFocusField).focus();}
document.getElementById(vFocusField).setActive();}catch(e)
{}}}}
function validateChangedText(pObject)
{if(!fieldValueHasChanged(pObject))return;pObject.value=trim(pObject.value)
pObject.prevValue=pObject.value
setPageChanged(pObject)}
function setPageChanged(pObject)
{if(pObject.ignorechange==true)return;gPageChanged=true;}
function fieldValueHasChanged(pObject)
{if(pObject.prevValue==pObject.value)return false;return true;}
function onbeforedeactivateLookup(pObject)
{if(window.event.toElement==null)return;if(window.event.toElement.id==pObject.id+"_lu"||window.event.toElement.relatedfield==pObject.id)
{pObject.imageClicked='true'
window.event.toElement.fromfield=pObject.id;}}
function onactivateLookup(pObject)
{if(window.event.fromElement!==null)
{if(pObject.id+"_lu"==window.event.fromElement.id)return;}
pObject.imageClicked="false";if(typeof pObject.lastValidatedValue=="undefined"||(pObject.validated=="true"&&pObject.lastValidatedValue==""))
{pObject.lastValidatedValue=pObject.value;}
else if(pObject.value=="")
{pObject.lastValidatedValue="";}}
function onLovImageClicked(pId)
{var vObject=document.getElementById(pId);if(isObject(vObject))
vObject.imageClicked='true';}
function displayLookupOnKey(pObject)
{switch(window.event.keyCode)
{case 120:var vImgObj=document.getElementById(pObject.id+'_lu');if(vImgObj==null&&isObject(pObject.lovimagefield))
vImgObj=document.getElementById(pObject.lovimagefield+'_lu');if(vImgObj!=null)
{if(vImgObj.currentStyle.visibility!="hidden")
vImgObj.focus();else
setFieldValue(pObject.id,pObject.value);vImgObj.fireEvent("onclick");}
break;}
return;}
function isObject(pObject)
{var vResult=(typeof(pObject)!="undefined"&&pObject!=null)?true:false;return vResult;}
function truncateNumber(pNumber)
{return Math.floor(pNumber)}
function lTrim(s)
{s=s.replace(/^\s*/,"")
s=s.replace(/^\n*/,"")
s=s.replace(/^\t*/,"")
return s}
function rTrim(s)
{s=s.replace(/\s*$/,"")
s=s.replace(/\t*$/,"")
s=s.replace(/\n*$/,"")
return s}
function trim(s)
{s=lTrim(rTrim(s))
return s}
function soundError()
{soundFile(SOUND_ERROR)}
function soundFile(pFileName)
{return;var vSoundPlayer=document.getElementById("soundplayer");if(vSoundPlayer!=null)
{document.all["soundplayer"].src=pFileName}}
function checkRequiredPage()
{var vPageOkay=true;var vFormOkay=true
if(gPageName=="BSWSPE"&&isObject(window.checkWSPromptRequiredPage))
{vPageOkay=checkWSPromptRequiredPage();}
else
{for(var i=0;i<document.forms.length;i++)
{vFormOkay=checkRequiredForm(document.forms[i]);if(!vFormOkay)vPageOkay=false;}}
if(!vPageOkay)
{displayStandardMessageOnPage(0,"error",MSG_ERR_PAGE_REQUIRED)}
return vPageOkay;}
function checkRequiredForm(pFormToCheck)
{var vFormOkay=true;var vFieldVal;for(var i=0;i<pFormToCheck.length;i++)
{var e=pFormToCheck.elements[i];if((e.type=="text")||(e.type=="textarea")||(e.type=="password")||(e.type=="file"))
{if(e.id.indexOf("_")==-1||(e.id.indexOf("_MI")!=-1||e.id.indexOf("_HH")!=-1)||isCustomField(e.id))
{if(isObject(e.id)&&e.id!="")
{if(e.type=="textarea"&&getFieldValue("pagemode")=="view"&&isObject(eval("window.oHTMLEdit"))&&oHTMLEdit!=null&&oHTMLEdit.idTextArea==e.id)
{var oEditor=eval("idContent"+oHTMLEdit.oName);vFieldVal=trim(oEditor.document.body.innerText);}
else
{vFieldVal=getFieldValue(e.id);}
if(getFieldAttribute(e.id)=="required"&&isblank(vFieldVal))
{displayMessageOnField(e.id,MSG_ERR_FIELD_REQUIRED,true);gProcessEventActionType=null;vFormOkay=false;}}}}}
return vFormOkay;}
function isblank(s)
{if(s==null||trim(s)=="")return true;return false;}
function getElementPosition(pElement)
{var vSearch=1;if(typeof(pElement)=="object")
{var vObjToCheck=pElement;}
else
{var vObjToCheck=document.getElementById(pElement);}
var vTagName=vObjToCheck.tagName;var vLeft=0
var vTop=0
do
{if(vTagName!="")
{if(vTagName.toLowerCase()!="body")
{vLeft=vLeft+vObjToCheck.offsetLeft;vTop=vTop+vObjToCheck.offsetTop;if(vTagName.toLowerCase()=="table"&&vObjToCheck.border!=0)
{vLeft=vLeft+1;vTop=vTop+1;}
vObjToCheck=vObjToCheck.offsetParent;vTagName=vObjToCheck.tagName;}
else
{vSearch=0;}}
else
{vSearch=0;}}
while(vSearch==1);var rvCoordinates=new Array(vLeft,vTop);return rvCoordinates;}
function showVerticalScrollBar(){if(!window.ignoreVerticalOverflow){document.body.style.overflowY="";}}
function hideVerticalScrollBar(){if(!window.ignoreVerticalOverflow){document.body.style.overflowY="hidden";}}
function hasVerticalScrollBar(){return((!window.ignoreVerticalOverflow)&&(document.body.scrollHeight>document.body.offsetHeight));}
function setElementPosition(pElementToMove,pElementToMoveTo,pXPad,pYPad,pHAlign,pVAlign,pHDraw,pVDraw)
{var vSrcWasHidden=false;var vXPad;var vYPad;var vPos=getElementPosition(pElementToMoveTo);if(typeof(pElementToMove)=="object")
{var vSourceElement=pElementToMove;}
else
{var vSourceElement=document.getElementById(pElementToMove);}
if(typeof(pElementToMoveTo)=="object")
{var vTargetElement=pElementToMoveTo;}
else
{var vTargetElement=document.getElementById(pElementToMoveTo);}
if(vSourceElement.style.display=="none")
{vSrcWasHidden=true;vSourceElement.style.display="block";}
var vPosLeft=vPos[0];var vPosTop=vPos[1];var vTargetWidth=vTargetElement.offsetWidth;var vTargetHeight=vTargetElement.offsetHeight;var vSourceWidth=vSourceElement.offsetWidth;var vSourceHeight=vSourceElement.offsetHeight;var vPageBottom=document.body.scrollHeight;var vPageEdge=document.body.scrollWidth;var divcontainment=false;var vDivElement=vTargetElement;while(vDivElement.tagName.toLowerCase()!="body")
{if(vDivElement.tagName.toLowerCase()=="div"&&vDivElement.conttype!=null)
{divcontainment=true;break;}
vDivElement=vDivElement.parentElement;}
if(divcontainment==true)
{var vScreenBottom=vDivElement.offsetTop+vDivElement.offsetHeight;var vScreenEdge=vDivElement.offsetLeft+vDivElement.offsetWidth;if(vDivElement.style.position=="absolute"&&vDivElement.offsetHeight<document.body.clientHeight)
{vPosTop-=vDivElement.offsetTop;if(vPosTop+vTargetHeight+vSourceHeight<=vDivElement.offsetHeight)
{vPosTop+=vTargetHeight;}
else if(vPosTop-vSourceHeight>=0)
{vPosTop-=vSourceHeight;}
else
{var vSpaceAbove=vPosTop;var vSpaceBelow=vDivElement.offsetHeight-vPosTop-vTargetHeight;if(vSpaceBelow>=vSpaceAbove)
{vPosTop+=vTargetHeight;vSourceElement.style.height=vSpaceBelow;}
else
{vPosTop=0;vSourceElement.style.height=vSpaceAbove;}}
vSourceElement.style.position="absolute";vSourceElement.style.posLeft=vPosLeft;vSourceElement.style.posTop=vPosTop;return;}}
else
{var vScreenBottom=document.body.scrollTop+document.body.clientHeight;var vScreenEdge=document.body.scrollLeft+document.body.clientWidth;}
vXPad=(pXPad)?pXPad:0;vYPad=(pYPad)?pYPad:0;switch(pHAlign)
{case"left":break;case"center":vPosLeft+=vTargetWidth/2;break;case"right":vPosLeft+=vTargetWidth;break;default:pHAlign="left";}
switch(pVAlign)
{case"top":break;case"middle":vPosTop+=vTargetHeight/2;break;case"bottom":vPosTop+=vTargetHeight;break;default:pVAlign="bottom";vPosTop+=vTargetHeight;}
switch(pHDraw)
{case"left":vPosLeft=vPosLeft+vXPad;break;case"right":vPosLeft=vPosLeft-vSourceWidth-vXPad;break;default:pHDraw="left";vPosLeft=vPosLeft+vXPad;}
switch(pVDraw)
{case"up":vPosTop=vPosTop-vSourceHeight-vYPad;break;case"down":vPosTop=vPosTop+vYPad;break;default:pVDraw="down";vPosTop=vPosTop+vYPad;}
if(vPosTop+vSourceHeight>vScreenBottom)
{vPosTop=vPosTop-vSourceHeight;if(pVAlign=="bottom")
vPosTop=vPosTop-vTargetHeight-(2*vYPad);if(pVAlign=="top")
vPosTop=vPosTop+vTargetHeight-(2*vYPad);}
if(vPosTop<document.body.scrollTop)
{vPosTop=vPosTop+vSourceHeight;if(pVAlign=="top")
vPosTop=vPosTop+vTargetHeight+(2*vYPad);if(pVAlign=="bottom")
vPosTop=vPosTop-vTargetHeight+(2*vYPad);}
if(vPosLeft+vSourceWidth>vScreenEdge)
{vPosLeft=vPosLeft-vSourceWidth;if(pHAlign=="right")
vPosLeft=vPosLeft-vTargetWidth-(2*vXPad);if(pHAlign=="left")
vPosLeft=vPosLeft+vTargetWidth-(2*vXPad);}
if(vPosLeft<document.body.scrollLeft)
{vPosLeft=vPosLeft+vSourceWidth;if(pHAlign=="left")
vPosLeft=vPosLeft+vTargetWidth+(2*vXPad);if(pHAlign=="right")
vPosLeft=vPosLeft-vTargetWidth+(2*vXPad);}
if(vPosLeft<=document.body.scrollLeft)
vPosLeft=document.body.scrollLeft+3;if(vPosLeft+vSourceWidth>=vScreenEdge)
vPosLeft=vScreenEdge-vSourceWidth-3;if(vPosTop<=document.body.scrollTop)
vPosTop=document.body.scrollTop+3;if(vPosTop+vSourceHeight>=vScreenBottom)
vPosTop=vScreenBottom-vSourceHeight-3;vSourceElement.style.position="absolute";vSourceElement.style.posLeft=vPosLeft;vSourceElement.style.posTop=vPosTop;if(vSrcWasHidden)
{vSourceElement.style.display="none";}}
function ShowPageHelp(vFunction)
{var pageHelpFunc;if(isObject(vFunction))
{pageHelpFunc=vFunction;}
else
{if(!isblank(gPopupWindowParentPageName))
pageHelpFunc=gPopupWindowParentPageName;else
pageHelpFunc=gPageName;var vPageMode=getFieldValue("pagemode").toLowerCase();if(vPageMode!=null&&vPageMode=='editlayout')
pageHelpFunc='BXLAYO';}
var helpLanguage=gUserLanguage;if(isblank(helpLanguage))
helpLanguage='EN';if(gPageName=="EWSREP"||gPageName=="COGREP"||gPageName=="COGREPC")
{if(isObject(parent.gReportName)&&!isblank(parent.gReportName))
pageHelpFunc=parent.gReportName;else
pageHelpFunc=gUserFunctionName;}
pHelpURL='../help/'+helpLanguage+'/formhelp/helpframe.htm?pagename='+escape(pageHelpFunc)+',language='+escape(helpLanguage)+',stylecd='+escape(window.gStyleCD);if(isObject(gHelpWindow))
{gHelpWindow.focus();gHelpWindow.location.href=pHelpURL;}
else
{gHelpWindow=window.open(pHelpURL,"HELP","width=700,height=600,toolbar=1,status=0,resizable=1,Scrollbars=1");gHelpWindow.focus();}}
function ShowHelpTopics(){var vWindow=getBrowserWindow();vWindow.hideStatusMeter();var helpLanguage=gUserLanguage;if(isblank(helpLanguage))
helpLanguage='EN';pHelpURL='../help/'+helpLanguage+'/formhelp/helpframe.htm?pagename=topics,language='+escape(helpLanguage)+',stylecd='+escape(window.gStyleCD);var vHelpWindowOpened=false;if(isObject(gHelpWindow))
{gHelpWindow.location.href=pHelpURL;gHelpWindow.focus();}
else
{gHelpWindow=window.open(pHelpURL,"HELP","width=700,height=600,toolbar=1,status=0,resizable=1,Scrollbars=1");}}
function resetgHelpWindow()
{gHelpWindow=null;}
function ShowAboutWindow()
{var vWindow=getBrowserWindow();var posx=(window.document.body.clientWidth-700)/2;var posy=(window.document.body.clientHeight-460)/2;if(posx<100)
posx=242;if(posy<100)
posy=164;var pStyle="left="+posx.toString()+",top="+posy.toString()+",width=700,height=460,toolbar=0,status=0,resizable=1,Scrollbars=1";vWindow.hideStatusMeter();var vRet=window.open("../base/about?popup=TRUE","",pStyle);}
function MM_goToURL(){for(var i=0;i<(MM_goToURL.arguments.length-1);i+=2)
eval(MM_goToURL.arguments[i]+".location='"+MM_goToURL.arguments[i+1]+"'");document.MM_returnValue=false;}
var globalMenuObject=null;var globalMenuURL=null;var globalMenuSrc=null;function completeMainIframe(buttonClicked)
{if(buttonClicked==null)
buttonClicked=1;var obj=document.getElementById("MAINIFRAME");if(buttonClicked==1)
{if(isObject(eval("obj.contentWindow.saveTabContents"))&&!obj.contentWindow.saveTabContents(true))
return;if(gActiveMainIframe&&gActiveMainIframe.cached&&isObject(gActiveMainIframe.contentWindow.gActiveSubIFrame))
{gActiveMainIframe.contentWindow.gActiveSubIFrame.refreshtabdata=true;}
gMainFrameTimer=window.setTimeout("goMainFrameTimeout()",5);return;}
if(buttonClicked==-1)
{return;}
if(buttonClicked==0)
{if(gActiveMainIframe&&gActiveMainIframe.cached&&isObject(gActiveMainIframe.contentWindow.gActiveSubIFrame))
{gActiveMainIframe.contentWindow.gActiveSubIFrame.refreshtabdata=true;}
MM_goToSubframe(globalMenuObject,globalMenuURL,globalMenuSrc,false);}}
function completeMainIframeInDesigner(pButtonClicked)
{if(pButtonClicked==null)
pButtonClicked=1;if(pButtonClicked==-1)
return;var obj=document.getElementById("MAINIFRAME");if(pButtonClicked==1)
{obj.contentWindow.savelayout();obj.contentWindow.gActiveSubIFrame.contentWindow.gPageLayoutModified=false;gMainFrameTimer=window.setTimeout("goMainFrameTimeout()",5);return;}
if(pButtonClicked==0)
{MM_goToSubframe(globalMenuObject,globalMenuURL,globalMenuSrc,false);}}
function goMainFrameTimeout()
{var obj=document.getElementById("MAINIFRAME");if(isObject(eval("obj.contentWindow.gActiveSubIFrame"))&&isObject(obj.contentWindow.gActiveSubIFrame.contentWindow.gProcessEventTimer))
{gMainFrameTimer=window.setTimeout("goMainFrameTimeout()",5);}
else if(isObject(obj.contentWindow.gProcessEventTimer))
{gMainFrameTimer=window.setTimeout("goMainFrameTimeout()",5);}
else
{gMainFrameTimer=null;if(obj.contentWindow.checkMessageBar())
{MM_goToSubframe(globalMenuObject,globalMenuURL,globalMenuSrc,false)}}}
var subFrameSrcObj=null;function MM_goToSubframe(object,action,srcObject,checkForSave)
{if(checkForSave==null)
checkForSave=true;obj=document.getElementById(object);if(checkForSave&&object=="MAINIFRAME")
{if(gPageMode=='editlayout')
{tIdx=action.lastIndexOf("/");tAction=action.substring(tIdx+1,action.length);sysFunction=tAction.split("?")[0];if(sysFunction=="WSAREQ"||sysFunction=="OSGISS"||sysFunction=="WSVREQ"||sysFunction=="SSREQS"||sysFunction=="SSAREQ"||sysFunction=="SSVREQ"||sysFunction=="BSHOME"||sysFunction=="EWSREP"||sysFunction=="ISMONT"||sysFunction=="YSINTF"||sysFunction=="WSCALR"||sysFunction=="SSHCST"||sysFunction=="WSAPPR"||sysFunction=="COGREP"||sysFunction=="COGREPC"||sysFunction=="COGQREPC"||sysFunction=="BSREPT"||sysFunction=="PSINVL"||sysFunction=="WSRELJ"||sysFunction=="WSWOBU"||sysFunction=="WSBREA"||sysFunction=="BSMOST"||sysFunction=="SSMANP"||sysFunction=="SSSUPT"||sysFunction=="BSEXIM")
{displayStandardMessageOnPage("","warning",MSG_WRN_BXLAYO_LAYOUTCANNOTMODIFIED);return;}
if(isObject(obj.contentWindow.getPageLayoutModifiedFlag)&&obj.contentWindow.getPageLayoutModifiedFlag())
{globalMenuObject=object;globalMenuURL=action;globalMenuSrc=srcObject;confirmStandardMessage("completeMainIframeInDesigner");return;}}
else
{if(isObject(eval("obj.contentWindow.childFrameHasChanges")))
{if(obj.contentWindow.childFrameHasChanges()&&saveChangesOnForm(obj.contentWindow.gPageName))
{globalMenuObject=object;globalMenuURL=action;globalMenuSrc=srcObject;var vContentTab=obj.contentWindow.gActiveSubIFrame;if(gPageName=="TSTRAN"&&isObject(vContentTab)&&isObject(vContentTab.contentWindow.checkSavePendingData))
vContentTab.contentWindow.checkSavePendingData();else if(gPageName=="SSISSU"&&isObject(vContentTab)&&isObject(vContentTab.contentWindow.localConfirmStandardMessage))
vContentTab.contentWindow.localConfirmStandardMessage(false);else
confirmStandardMessage("completeMainIframe");return;}}}}
var fnames=getSysUserFuncNames(action);obj=makeMainIframe(fnames[1]);addToPageHistory(fnames[0],fnames[1],true);if(useCachedScreen(obj))
{showHideMainIframes(true);toggleMainPageStartCenter(false);return;}
TAB_reg=new RegExp("TAB:","i");subFrameSrcObj=srcObject;if(action.search(TAB_reg)!=-1)
{var vAllTables=document.getElementsByTagName("table");var vAllModuleTables=new Array();for(var i=0;i<vAllTables.length;i++)
{if(vAllTables[i].isModule=="true")
vAllModuleTables.push(vAllTables[i]);}
trElem=subFrameSrcObj.parentElement.parentElement
for(var i=0;i<vAllModuleTables.length;i++)
{if(vAllModuleTables[i].contains(trElem))
{gCurrentModuleTab=i;}}
pFieldValueString="MENU_MODULE_KEY="+gCurrentModuleTab
processEventCore(this,"MOREMENU",pFieldValueString,false);if(gProcessEventFoundStopError)
return false;}
var vWindow=getBrowserWindow();vWindow.showStatusMeter();setTimeout("MM_goToSubframeWindow('"+object+"','"+action+"')",200);}
function MM_goToSubframeWindow(object,action)
{var obj=document.getElementById(object);var JS_reg=new RegExp("JavaScript:","i");var WIN_reg=new RegExp("window:","i");var TAB_reg=new RegExp("TAB:","i");if(action.search(JS_reg)!=-1)
{action=action.replace(JS_reg,"");eval("obj.contentWindow."+action);}
else if(action.search(WIN_reg)!=-1)
{setSystemLogout();return;}
else if(action.search(TAB_reg)!=-1)
{action=action.replace(TAB_reg,"");action=action+"?MENU_MODULE_KEY="+gCurrentModuleTab
obj.sendhtmlto=object;obj.controltype="nonmodal"
processEventCore(obj,action,"");}
else
{if(obj!=null)
{vReq="MENU_MODULE_KEY="+gCurrentModuleTab+"&removescreenflows=yes";obj.sendhtmlto=object;obj.controltype="nonmodal";setUserSystemFunctionaction(action);processEventCore(obj,action,vReq);if(gErrorInMessages)
{gPageName=gLastPageName;gUserFunctionName=gLastUserFunction;restoreLastMainIframe();}
else
showHideMainIframes(false);}}
document.MM_returnValue=false;if(!gErrorInMessages&&!isExternalLink()&&isObject(window.toggleMainPageStartCenter))
toggleMainPageStartCenter(false);}
function isExternalLink()
{if(gPageName=="EWSWEB")
return true;else if(gPageName=="COGWEB"&&gUserFunctionName=="BSREPA")
return true;return false;}
var gLastPageName=null;var gLastUserFunction=null;function setUserSystemFunctionaction(action)
{if(isObject(action))
{var lInd=action.lastIndexOf("/");tAction=action.substring(lInd+1,action.length);var functions=tAction.split("?");gLastPageName=gPageName;gLastUserFunction=gUserFunctionName;gPageName=functions[0];gCurrentTab="";if(action.indexOf("USER_FUNCTION")!=-1)
gUserFunctionName=functions[1].split("=")[1];else
gUserFunctionName=functions[0];}}
function changeModuleTabs(vCurrentModuleTab)
{if(vCurrentModuleTab!=""&&vCurrentModuleTab!=null&&vCurrentModuleTab!='null')
gCurrentModuleTab=vCurrentModuleTab;try
{var vAllTables=document.getElementsByTagName("table");var vAllModuleTables=new Array();for(var i=0;i<vAllTables.length;i++)
{if(vAllTables[i].isModule=="true")
vAllModuleTables.push(vAllTables[i]);}
for(var i=0;i<vAllModuleTables.length;i++)
{if(i==gCurrentModuleTab)
{var allTds=vAllModuleTables[i].getElementsByTagName("td");for(var j=0;j<allTds.length;j++)
{if(allTds[j].className=="tableft")
allTds[j].className="tabselleft"
if(allTds[j].className=="tabright")
allTds[j].className="tabselright"
if(allTds[j].className=="tab")
allTds[j].className="tabsel"}}
else
{var allTds=vAllModuleTables[i].getElementsByTagName("td");for(var j=0;j<allTds.length;j++)
{if(allTds[j].className=="tabselleft")
allTds[j].className="tableft"
if(allTds[j].className=="tabselright")
allTds[j].className="tabright"
if(allTds[j].className=="tabsel")
allTds[j].className="tab"}}}}
catch(e)
{}}
function refresh()
{window.location.reload(false);}
function buttonpress(pStatus,pEvt)
{if(!isObject(pEvt))
{srcElem=window.event.srcElement}
else
{srcElem=pEvt.srcElement;}
while(srcElem.tagName!="TR"&&srcElem.tagName!="TABLE")
srcElem=srcElem.parentElement;if(srcElem.firstChild.nextSibling.className=="form"){if(pStatus=='up')
{srcElem.firstChild.firstChild.src=window.gStylePrePath+window.gStyleCD+"/btn_left.gif";srcElem.firstChild.nextSibling.background=window.gStylePrePath+window.gStyleCD+"/btn_middle.gif";srcElem.lastChild.firstChild.src=window.gStylePrePath+window.gStyleCD+"/btn_right.gif";}
else
{srcElem.firstChild.firstChild.src=window.gStylePrePath+window.gStyleCD+"/btn_left_dwn.gif";srcElem.firstChild.nextSibling.background=window.gStylePrePath+window.gStyleCD+"/btn_middle_dwn.gif";srcElem.lastChild.firstChild.src=window.gStylePrePath+window.gStyleCD+"/btn_right_dwn.gif";}}
else{if(pStatus=='up')
{srcElem.firstChild.firstChild.className="button_left_up";srcElem.firstChild.nextSibling.className="button_text_up";srcElem.lastChild.firstChild.className="button_right_up";}
else
{srcElem.firstChild.firstChild.className="button_left_down";srcElem.firstChild.nextSibling.className="button_text_down";srcElem.lastChild.firstChild.className="button_right_down";}}}
function buttonOnkeypress(pButtonObject)
{if(window.event.keyCode==13||window.event.keyCode==32)
{window.event.cancelBubble=true;event.returnValue=false;pButtonObject.fireEvent('onclick');return false;}}
function validateChangedDuration(pObject,pDisplayError)
{if(typeof(pDisplayError)=="undefined"||pDisplayError==null)
{pDisplayError=true;}
if(typeof(pObject)=="undefined"||pObject==null)
{pObject=window.event.srcElement;}
if(!fieldValueHasChanged(pObject))return;var vMinutes=convertDurationToStandardFormat(pObject.value);if(vMinutes!==false)
{var vDurationValue=convertDurationToClientFormat(vMinutes)
pObject.value=vDurationValue;pObject.prevValue=vDurationValue;setPageChanged(pObject)
clearDisplayFieldMessage(pObject.id)
if(pObject.custom_onblur)
{eval(pObject.custom_onblur)}
return true}
else
{if(pDisplayError==true)
{pObject.prevValue="InputError"
soundError()
displayMessageOnField(pObject.id,MSG_ERR_FIELD_INVALID_DURATION)
pObject.select();window.event.returnValue=false;}
else
{return MSG_ERR_FIELD_INVALID_DURATION;}}}
function convertDurationToStandardFormat(pString)
{if(pString=="")return 0;var vChar;var vMinutes=0
var vTime='';var vNegVal=false;pString=trim(pString);var vPattern=new RegExp("[0123456789-]")
var vPatternNegSym=new RegExp("^"+UL.NEGSYMBOL);if(pString.search(vPatternNegSym)!=-1)
{vNegVal=true;pString=pString.replace(vPatternNegSym,"");}
if(pString.search(UL.NEGSYMBOL)!=-1)
{return false;}
for(var i=0;i<pString.length;i++)
{vChar=pString.charAt(i);if(vChar==" ")
{continue;}
if(vPattern.test(vChar))
{vTime=vTime+vChar;continue;}
vTime=parseInt(vTime,10)
if(isNaN(vTime))return false;switch(vChar)
{case UL.DURATIONDAYS:vMinutes=vMinutes+(vTime*24*60);break;case UL.DURATIONHOURS:vMinutes=vMinutes+(vTime*60);break;case UL.DURATIONMINUTES:vMinutes=vMinutes+vTime;break;default:return false;}
vTime=''}
if(vMinutes==0&&vTime!=='')
{vMinutes=parseInt(vTime,10)
if(isNaN(vTime))return false;}
if(vNegVal)
{vMinutes=vMinutes*-1;}
return vMinutes}
function convertDurationToClientFormat(pMinutes)
{var vFormatted='';var vInt;var vDecimalPos;var vNegativeFlag=false;if(pMinutes=="")return""
if(isNaN(parseInt(pMinutes,10)))return pMinutes
pMinutes=parseInt(pMinutes,10)
if(pMinutes<0)
{vNegativeFlag=true;pMinutes=pMinutes*-1}
vInt=truncateNumber(pMinutes/(24*60))
if(vInt>0)
{vFormatted=vFormatted+' '+vInt.toString()+UL.DURATIONDAYS
pMinutes=pMinutes-(vInt*24*60)}
vInt=truncateNumber(pMinutes/60)
if(vInt>0)
{vFormatted=vFormatted+' '+vInt.toString()+UL.DURATIONHOURS
pMinutes=pMinutes-(vInt*60)}
vInt=pMinutes;if(vInt>0)
{vFormatted=vFormatted+' '+vInt.toString()+UL.DURATIONMINUTES
pMinutes=0;}
vFormatted=trim(vFormatted)
if(vNegativeFlag)
{vFormatted="-"+vFormatted;}
return vFormatted;}
var gByMonthFixedDay=0;var gByMonthLatestDay=0;var gCalSplitDate=new Array()
var gCalFieldRef
var gCalTime;function displayCalendarOnKey(pObject)
{var vShow=false;switch(window.event.keyCode)
{case 120:vShow=true;break;case 38:if(event.altKey)vShow=true;break;case 40:if(event.altKey)vShow=true;break;}
if(vShow)
{if(pObject.getAttribute("fieldtype")==EG_DATE_FIELD)
{showPopUpCalendar(pObject);}
else
{vFieldLookup=pObject.fieldlookup;showPopUpCalendar(document.getElementById(vFieldLookup));}}
window.event.returnValue=false;}
function makeFakePositionsForCalendar(pElem,pWidth,pHeight)
{var vFakeDiv=window.document.createElement("DIV")
vFakeDiv.style.visibility="hidden";vFakeDiv.style.width=pWidth;vFakeDiv.style.height=pHeight;vFakeDiv.id="FakeDiv";setElementPosition(vFakeDiv,pElem.fieldid,0,0);var returnArray=new Array(2);returnArray[0]=vFakeDiv.style.posLeft;returnArray[1]=vFakeDiv.style.posTop;vFakeDiv=null;return returnArray;}
function showPopUpCalendar(pElem)
{var vField;if(pElem.getAttribute("fieldtype")==EG_DATE_FIELD)
{vField=pElem;}else
{vField=document.getElementById(pElem.fieldid);}
var vDivX=window.event.x
var vDivY=window.event.y
var vDivWidth=260;var vDivHeight;var vDivMargin=0
if(vField.datatype.toLowerCase()=="date")
{vDivHeight=195;}
else
{vDivHeight=245;}
var vLocArray;if(pElem.getAttribute("fieldtype")==EG_DATE_FIELD)
{vLocArray=makeFakePositionsForCalendar(document.getElementById(pElem.fieldlookup),vDivWidth,vDivHeight);}else
{vLocArray=makeFakePositionsForCalendar(pElem,vDivWidth,vDivHeight)}
var url="../base/displayCalendarPopup";var style="help:no; status:0; resizable:no; dialogWidth:"+vDivWidth+"px; dialogHeight:"+vDivHeight+"px; dialogLeft:"+vLocArray[0]+"; dialogTop:"+vLocArray[1]+";";var vCalendarArray=new Array();vCalendarArray[0]=vField;vCalendarArray[1]=window;var vReturnField=dstmShowModalDialog(url,vCalendarArray,style,true);if(vReturnField.custom_onblur)
{fireCustomEvent(vReturnField,"custom_onblur");}
setPageChanged(vField);vField.focus();clearDisplayFieldMessage(vField.id);}
function displayCalendar(pField,pSourceWindow)
{var vCalendar=document.all("iframeCalendar")
gCalFieldRef=pField;window.returnValue=gCalFieldRef;var vDataField=pField;var vDataType=vDataField.datatype;if(!isObject(vDataType))
{return;}
var vCurrentValue=trim(vDataField.value);var exp=/^[+-]/;if(vCurrentValue.search(exp)!=-1)
{vDataField.value="";vCurrentValue="";}
var vDateValid="";if(vDataType.toLowerCase()!="datetime")
{if(isObject(vCurrentValue)&&vCurrentValue!="")
{vDateValid=validateChangedDate(vDataField,false,false);if(!vDateValid||vDateValid==MSG_ERR_FIELD_INVALID_DATE)
{vDataField.value="";vCurrentValue="";}}
if(vCurrentValue=="")
{var vToday=new Date(pSourceWindow.getOrgServerTime());gCalSplitDate.year=vToday.getFullYear();gCalSplitDate.month=vToday.getMonth()+1;gCalSplitDate.day=vToday.getDate();gCalTime=null;}
else
{gCalTime=null;var vTempCalSplitDate=splitYearMonthDay(vCurrentValue)
vTempCalSplitDate.day=parseInt(vTempCalSplitDate.day,10);vTempCalSplitDate.month=parseInt(vTempCalSplitDate.month,10);vTempCalSplitDate.year=parseInt(vTempCalSplitDate.year,10);gCalSplitDate=vTempCalSplitDate;if(!isValidDate(gCalSplitDate))return}}
else
{if(isObject(vCurrentValue)&&vCurrentValue!="")
{vDateValid=validateChangedDatetime(vDataField,false);if(!vDateValid||vDateValid==MSG_ERR_FIELD_INVALID_DATE)
{vDataField.value="";vCurrentValue="";}}
gCalTime=new Object();if(vCurrentValue=="")
{var vToday=new Date(pSourceWindow.getOrgServerTime());gCalSplitDate.year=vToday.getFullYear();gCalSplitDate.month=vToday.getMonth()+1;gCalSplitDate.day=vToday.getDate();gCalTime.hours=vToday.getHours();gCalTime.minutes=vToday.getMinutes();}
else
{var vDate=splitDatetime(vCurrentValue);var vTempCalSplitDate=splitYearMonthDay(vDate[0])
vTempCalSplitDate.day=parseInt(vTempCalSplitDate.day,10);vTempCalSplitDate.month=parseInt(vTempCalSplitDate.month,10);vTempCalSplitDate.year=parseInt(vTempCalSplitDate.year,10);gCalSplitDate=vTempCalSplitDate;if(!isObject(vDate[1])||vDate[1]=="")
{vDate[1]="00:00"}
var vTimeArray=vDate[1].split(":");gCalTime.hours=trim(vTimeArray[0]);gCalTime.minutes=trim(vTimeArray[1]);}}
if(vCalendar.src!=="../dstmCalendar.htm")
{vCalendar.src="../dstmCalendar.htm";vCalendar.scrolling="no";}
else
{document.frames("iframeCalendar").setCalendarDate(gCalSplitDate,gCalTime)}
vCalendar.style.position="absolute"
vCalendar.style.display="block";vCalendar.style.width=window.document.body.clientWidth;vCalendar.style.height=window.document.body.clientHeight;vCalendar.style.border="0px solid black";vCalendar.focus()}
function replaceAlphaMonth(pInputDate)
{var vWorkDate
vWorkDate=pInputDate.toLowerCase();var vSplitDate=vWorkDate.split("/");var vMonthPosition=-1;var vSplitFormat=UL.DATEPARTS;for(var r=0;r<vSplitFormat.length;r++)
{if(vSplitFormat[r].indexOf("m")!=-1)
{vMonthPosition=r;break;}}
if(vMonthPosition!=-1)
{var vMonthPart=vWorkDate.split("/")[vMonthPosition];for(v=0;v<UL.MONTHSHORT.length;v++)
{if(isObject(vMonthPart)&&vMonthPart.toLowerCase()==UL.MONTHSHORT[v].toLowerCase())
{if(v<9)
{vSplitDate[vMonthPosition]="0"+(v+1).toString();}
else
{vSplitDate[vMonthPosition]=v+1;}
break;}}
vWorkDate=vSplitDate.join("/");}
return vWorkDate}
function setCentury(pSplitDate)
{switch(pSplitDate.year.toString().length)
{case 0:pSplitDate.year=(new Date).getFullYear()
break;case 1:pSplitDate.year='200'+pSplitDate.year
break;case 2:if(pSplitDate.year-0>49){pSplitDate.year='19'+pSplitDate.year}
else{pSplitDate.year='20'+pSplitDate.year}
break;}
return pSplitDate}
function splitYearMonthDay(pInputDate,pStdFormatInput)
{var vDateFormatOrder;if(pStdFormatInput)
{vDateFormatOrder="mdy";vDateSeperator="-";}
else
{vDateFormatOrder=UL.DATEPARTS[0].substring(0,1)+UL.DATEPARTS[1].substring(0,1)+UL.DATEPARTS[2].substring(0,1)
vDateSeperator=UL.DATESEPARATOR;}
var vIndex
var vSplitInput
var vSplitDate=new Object()
var vToday=new Date()
pInputDate=trim(pInputDate)
if(pInputDate.length==0)return""
vSplitDate.year=0;vSplitDate.month=0;vSplitDate.day=0;vSplitDate.error=false;if(vDateSeperator.length>0&&vDateSeperator!=="/")
{while(true)
{vIndex=pInputDate.indexOf(vDateSeperator)
if(vIndex<0){break}
pInputDate=pInputDate.substring(0,vIndex)+"/"+pInputDate.substr(vIndex+1)}}
while(true)
{vIndex=pInputDate.indexOf(" ")
if(vIndex<0){break}
pInputDate=pInputDate.substring(0,vIndex)+"/"+pInputDate.substr(vIndex+1)}
if(!pStdFormatInput)
pInputDate=replaceAlphaMonth(pInputDate)
if(pInputDate.indexOf("/")>0)
{vSplitInput=pInputDate.split("/")
if(vSplitInput.length>3)
{vSplitDate.error=true;}
if(vSplitInput.length==2)
{vSplitDate.year=vToday.getFullYear();switch(vDateFormatOrder)
{case'mdy':case'ymd':vSplitDate.month=vSplitInput[0]
vSplitDate.day=vSplitInput[1]
break;case'dmy':vSplitDate.day=vSplitInput[0]
vSplitDate.month=vSplitInput[1]
break;}}
else
{switch(vDateFormatOrder)
{case'mdy':vSplitDate.month=vSplitInput[0]
vSplitDate.day=vSplitInput[1]
vSplitDate.year=vSplitInput[2]
break;case'dmy':vSplitDate.day=vSplitInput[0]
vSplitDate.month=vSplitInput[1]
vSplitDate.year=vSplitInput[2]
break;case'ymd':vSplitDate.year=vSplitInput[0]
vSplitDate.month=vSplitInput[1]
vSplitDate.day=vSplitInput[2]
break;}}
vSplitDate=setCentury(vSplitDate)
if(vSplitDate.day.length<2)
{vSplitDate.day="0"+vSplitDate.day;}
if(vSplitDate.month.length<2)
{vSplitDate.month="0"+vSplitDate.month;}
if(vSplitDate.day.length>2||vSplitDate.month.length>2||vSplitDate.year.length>4)
vSplitDate.error=true;return vSplitDate;}
switch(pInputDate.length)
{case 0:case 1:vSplitDate.year=vToday.getFullYear();vSplitDate.month=vToday.getMonth()+1;vSplitDate.day=vToday.getDate();break;case 2:vSplitDate.year=vToday.getFullYear()
switch(vDateFormatOrder){case"mdy":case"ymd":vSplitDate.month=pInputDate.substring(0,1)
vSplitDate.day=pInputDate.substring(1,2)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,1)
vSplitDate.month=pInputDate.substring(1,2)
break;}
break;case 3:vSplitDate.year=vToday.getFullYear()
switch(vDateFormatOrder){case"mdy":case"ymd":vSplitDate.month=pInputDate.substring(0,2)
vSplitDate.day=pInputDate.substring(2,3)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,1)
vSplitDate.month=pInputDate.substring(1,3)
break;}
if(isValidDate(vSplitDate)){break}
switch(vDateFormatOrder){case"mdy":case"ymd":vSplitDate.month=pInputDate.substring(0,1)
vSplitDate.day=pInputDate.substring(1,3)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,3)
break;}
break;case 4:vSplitDate.year=vToday.getFullYear()
switch(vDateFormatOrder){case"mdy":case"ymd":vSplitDate.month=pInputDate.substring(0,2)
vSplitDate.day=pInputDate.substring(2,4)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,4)
break;}
if(isValidDate(vSplitDate)){break}
switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,1)
vSplitDate.day=pInputDate.substring(1,2)
vSplitDate.year=pInputDate.substring(2,4)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,3)
vSplitDate.day=pInputDate.substring(3,4)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,1)
vSplitDate.month=pInputDate.substring(1,2)
vSplitDate.year=pInputDate.substring(2,4)
break;}
vSplitDate=setCentury(vSplitDate)
if(isValidDate(vSplitDate)){break}
switch(vDateFormatOrder){case"mdy":case"ymd":vSplitDate.month=pInputDate.substring(0,3)
vSplitDate.day=pInputDate.substring(3,4)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,1)
vSplitDate.month=pInputDate.substring(1,4)
break;}
vSplitDate=setCentury(vSplitDate)
break;case 5:switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,2)
vSplitDate.day=pInputDate.substring(2,3)
vSplitDate.year=pInputDate.substring(3,5)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,4)
vSplitDate.day=pInputDate.substring(4,5)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,1)
vSplitDate.month=pInputDate.substring(1,3)
vSplitDate.year=pInputDate.substring(3,5)
break;}
vSplitDate=setCentury(vSplitDate)
if(isValidDate(vSplitDate)){break}
switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,1)
vSplitDate.day=pInputDate.substring(1,3)
vSplitDate.year=pInputDate.substring(3,5)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,3)
vSplitDate.day=pInputDate.substring(3,5)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,3)
vSplitDate.year=pInputDate.substring(3,5)
break;}
vSplitDate=setCentury(vSplitDate)
if(isValidDate(vSplitDate)){break}
switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,2)
vSplitDate.day=pInputDate.substring(2,4)
vSplitDate.year=pInputDate.substring(4,5)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,1)
vSplitDate.month=pInputDate.substring(1,3)
vSplitDate.day=pInputDate.substring(3,5)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,4)
vSplitDate.year=pInputDate.substring(4,5)
break;}
vSplitDate=setCentury(vSplitDate)
switch(vDateFormatOrder){case"mdy":case"ymd":vSplitDate.month=pInputDate.substring(0,3)
vSplitDate.day=pInputDate.substring(3,5)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,5)
break;}
vSplitDate=setCentury(vSplitDate)
break;case 6:switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,2)
vSplitDate.day=pInputDate.substring(2,4)
vSplitDate.year=pInputDate.substring(4,6)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,4)
vSplitDate.day=pInputDate.substring(4,6)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,4)
vSplitDate.year=pInputDate.substring(4,6)
break;}
vSplitDate=setCentury(vSplitDate)
if(isValidDate(vSplitDate)){break}
switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,1)
vSplitDate.day=pInputDate.substring(1,2)
vSplitDate.year=pInputDate.substring(2,6)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,4)
vSplitDate.month=pInputDate.substring(4,5)
vSplitDate.day=pInputDate.substring(5,6)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,1)
vSplitDate.month=pInputDate.substring(1,2)
vSplitDate.year=pInputDate.substring(2,6)
break;}
break;case 7:switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,2)
vSplitDate.day=pInputDate.substring(2,3)
vSplitDate.year=pInputDate.substring(3,7)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,4)
vSplitDate.month=pInputDate.substring(4,6)
vSplitDate.day=pInputDate.substring(6,7)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,1)
vSplitDate.month=pInputDate.substring(1,3)
vSplitDate.year=pInputDate.substring(3,7)
break;}
if(isValidDate(vSplitDate)){break}
switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,1)
vSplitDate.day=pInputDate.substring(1,3)
vSplitDate.year=pInputDate.substring(3,7)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,4)
vSplitDate.month=pInputDate.substring(4,5)
vSplitDate.day=pInputDate.substring(5,7)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,3)
vSplitDate.year=pInputDate.substring(3,7)
break;}
break;case 8:switch(vDateFormatOrder){case"mdy":vSplitDate.month=pInputDate.substring(0,2)
vSplitDate.day=pInputDate.substring(2,4)
vSplitDate.year=pInputDate.substring(4,8)
break;case"ymd":vSplitDate.year=pInputDate.substring(0,4)
vSplitDate.month=pInputDate.substring(4,6)
vSplitDate.day=pInputDate.substring(6,8)
break;case"dmy":vSplitDate.day=pInputDate.substring(0,2)
vSplitDate.month=pInputDate.substring(2,4)
vSplitDate.year=pInputDate.substring(4,8)
break;}
break;}
if(vSplitDate.day.length<2)
{vSplitDate.day="0"+vSplitDate.day;}
if(vSplitDate.month.length<2)
{vSplitDate.month="0"+vSplitDate.month;}
if(vSplitDate.day.length>2||vSplitDate.month.length>2||vSplitDate.year.length>4)
vSplitDate.error=true;return vSplitDate;}
function convertDateToStdFormat(pValue)
{if(pValue=="")
{return"";}
var vSplitDate=new Object()
vSplitDate=splitYearMonthDay(pValue)
if((vSplitDate.year+vSplitDate.month+vSplitDate.day)=="")return"";return vSplitDate.month+'/'+vSplitDate.day+"/"+vSplitDate.year}
function getDateSegment(splitDate,dateFormatSegment){switch(dateFormatSegment)
{case'mm':return splitDate.month.toString();case'mmm':return UL.MONTHSHORT[splitDate.month-1];case'dd':return splitDate.day.toString();case'yy':return splitDate.year.toString().substring(2,4);case'yyyy':return splitDate.year.toString();}}
function getDateSeparator(){return UL.DATESEPARATOR;}
function getDateFormatPart(partNumber){return UL.DATEPARTS[partNumber];}
function convertDateToClientFormat(splitDate)
{if(isObject(splitDate)&&isObject(splitDate.day)&&isObject(splitDate.month))
{if(splitDate.month.toString().length<2)
{splitDate.month="0"+splitDate.month.toString();}
if(splitDate.day.toString().length<2)
{splitDate.day="0"+splitDate.day.toString();}}
var formattedDate="";var dateSeparator=getDateSeparator();formattedDate+=getDateSegment(splitDate,getDateFormatPart(0));formattedDate+=dateSeparator;formattedDate+=getDateSegment(splitDate,getDateFormatPart(1));formattedDate+=dateSeparator;formattedDate+=getDateSegment(splitDate,getDateFormatPart(2));return formattedDate;}
function isValidDate(pSplitDate)
{if(typeof(pSplitDate)=="string")
{return false;}
re=/^\d+$/;if(!re.test(pSplitDate.year.toString())||!re.test(pSplitDate.month.toString())||!re.test(pSplitDate.day.toString()))
{return false;}
var vWorkDate=new Date(pSplitDate.year,pSplitDate.month-1,pSplitDate.day)
if(pSplitDate.year-0!=vWorkDate.getFullYear()||vWorkDate.getFullYear()>giMaxYear||vWorkDate.getFullYear()<giMinYear){return false}
if(pSplitDate.month-1!=vWorkDate.getMonth()){return false}
if(pSplitDate.day-0!=vWorkDate.getDate()){return false}
return true;}
function datePlusMinus(dateField,includeTime){var keyPressed=window.event.keyCode;if(!isObject(keyPressed)){return;}
var isSpace=(keyPressed==32);var isPlusSign=(keyPressed==43);var isMinusSign=(keyPressed==45);if(isSpace&&isblank(dateField.value)){initWithTodaysDate(dateField,includeTime);}else if(isPlusSign){incrementDate(dateField,includeTime);}else if(isMinusSign){decrementDate(dateField,includeTime);}}
function incrementDate(field,includeTime){incrementOrDecrementDate(field,1,includeTime);}
function decrementDate(field,includeTime){incrementOrDecrementDate(field,-1,includeTime);}
function incrementOrDecrementDate(dateField,numDays,includeTime){if(isblank(dateField.value)){initWithTodaysDate(dateField,includeTime);}else{var standardFormatDate=toStandardFormat(dateField.value,includeTime);if(!isblank(standardFormatDate)){var date=new Date(standardFormatDate);if(!isNaN(date)){dateField.value=addDaysToDate(date,numDays,includeTime).toString();window.event.returnValue=false;}}}}
function toStandardFormat(dateValue,includeTime){if(isCompleteDate(dateValue)){return(includeTime)?convertDatetimeToStdFormat(dateValue):convertDateToStdFormat(dateValue);}
return null;}
function isCompleteDate(dateValue){if(!isblank(dateValue)){var dateParts=dateValue.split('-');dateParts=(dateParts.length==3)?dateParts:dateValue.split('/');dateParts=(dateParts.length==3)?dateParts:dateValue.split('.');return((dateParts.length==3)&&(!hasBlankElements(dateParts)));}
return false;}
function hasBlankElements(array){for(var i=0,n=array.length;i<n;i++){if(isblank(array[i])){return true;}}
return false;}
function validateChangedDateTimeInt(pObject,pDisplayError)
{var vDateValue=trim(pObject.value)
if(isObject(vDateValue)&&vDateValue!=""&&(vDateValue.charAt(0)=="+"||vDateValue.charAt(0)=="-"))
{if(!isNaN(vDateValue))
{pObject.prevValue=vDateValue;clearDisplayFieldMessage(pObject.id)
return true;}}
if(pObject.datatype.toUpperCase()=="DATE")
return validateChangedDate(pObject,false);else
return validateChangedDatetime(pObject,false);}
function validateChangedDate(pObject,pDisplayError,pCallCustomOnBlur)
{if(!isObject(pCallCustomOnBlur))
{pCallCustomOnBlur=true;}
if(typeof(pDisplayError)=="undefined"||pDisplayError==null)
{pDisplayError=true;}
if(typeof(pObject)=="undefined"||pObject==null)
{pObject=window.event.srcElement;}
var vDateValue;if(pObject.tagName=="DIV")
{vDateValue=pObject.innerText;}
else
{vDateValue=trim(pObject.value);}
if(vDateValue=="")
{pObject.prevValue="";clearDisplayFieldMessage(pObject.id)
if(pObject.invoke_customonblur==true)
eval(pObject.custom_onblur)
return true;}
if(!fieldValueHasChanged(pObject))return true;var vSplitDate=new Object()
try
{vSplitDate=splitYearMonthDay(vDateValue)}
catch(e)
{vSplitDate=MSG_ERR_FIELD_INVALID_DATE;}
if(!vSplitDate.error&&isValidDate(vSplitDate))
{vDateValue=convertDateToClientFormat(vSplitDate)
pObject.value=vDateValue;pObject.prevValue=vDateValue;setPageChanged(pObject)
clearDisplayFieldMessage(pObject.id)
if(pObject.custom_onblur&&pCallCustomOnBlur)
{eval(pObject.custom_onblur)}
return true}
else
{if(pDisplayError==true)
{pObject.prevValue="InputError"
soundError()
if(pObject.getAttribute("fieldtype")==EG_DATE_FIELD)
{displayMessageOnField(pObject,MSG_ERR_FIELD_INVALID_DATE);}else
{displayMessageOnField(pObject.id,MSG_ERR_FIELD_INVALID_DATE)}
pObject.select();window.event.returnValue=false;return false;}
else
{return MSG_ERR_FIELD_INVALID_DATE;}}}
function convertDatetimeToStdFormat(pValue)
{var vDatePortion=pValue;var vTimePortion='';var vValue=pValue;var vArray=new Array;vArray=splitDatetime(vValue);vDatePortion=vArray[0];vTimePortion=vArray[1];vDatePortion=convertDateToStdFormat(vDatePortion)
if(vDatePortion=="")return"";return vDatePortion+vTimePortion;}
function convertDatetimeToClientFormat(pValue)
{var vDatePortion=pValue
var vTimePortion='';var vValue=pValue;var vArray=new Array;vArray=splitDatetime(vValue);vDatePortion=vArray[0];vTimePortion=vArray[1];var vSplitDate=new Object();vSplitDate=splitYearMonthDay(vDatePortion,true);vDatePortion=convertDateToClientFormat(vSplitDate);if(vDatePortion=="")return"";return vDatePortion+vTimePortion;}
function validateTime(pValue)
{var vTime=new Array;var vInt;var vFormatted;var vChar;vTime=pValue.split(":")
if(vTime.length>0)
{vChar=vTime[0].charAt(0);if(vChar=='0')
{vTime[0]=vTime[0].substring(1)}
vInt=parseInt(vTime[0],10);if(isNaN(vInt))return false;if(vInt<0||vInt>23)return false;if(vInt<10)
{vTime[0]="0"+vInt.toString();}
else
{vTime[0]=vInt.toString();}}
if(vTime.length>1)
{vChar=vTime[1].charAt(0);if(vChar=='0')
{vTime[1]=vTime[1].substring(1)}
vInt=parseInt(vTime[1],10)
if(isNaN(vInt))return false;if(vInt<0||vInt>59)return false;if(vInt<10)
{vTime[1]="0"+vInt.toString();}
else
{vTime[1]=vInt.toString();}}
else
{vTime[1]="00";}
if(vTime.length>2)
{vChar=vTime[2].charAt(0);if(vChar=='0')
{vTime[2]=vTime[2].substring(1)}
vInt=parseInt(vTime[2],10)
if(isNaN(vInt))return false;if(vInt<0||vInt>59)return false;if(vInt<10)
{vTime[2]="0"+vInt.toString();}
else
{vTime[2]=vInt.toString();}}
vFormatted=vTime[0]+UL.TIMESEPARATOR+vTime[1]
if(vTime.length>2)
{vFormatted=vFormatted+UL.TIMESEPARATOR+vTime[2]}
return vFormatted;}
function splitDatetime(pValue)
{var vDatePortion=pValue;var vTimePortion='';var vSeparatorFound=false;if(UL.DATESEPARATOR!==" ")
{vSeparatorFound=true;}
for(var i=pValue.length;i--;i>=0)
{if(vSeparatorFound)
{if(pValue.charAt(i)==' ')
{vDatePortion=pValue.substring(0,i);vTimePortion=trim(pValue.substring(i))
var vNewTime=validateTime(vTimePortion)
if(vNewTime==false)
{return false;}
vTimePortion=vNewTime;if(vTimePortion.length>0)
{vTimePortion=' '+vTimePortion;}
break;}
else
{continue;}}
if(pValue.charAt(i)==UL.TIMESEPARATOR)
{vSeparatorFound=true;}}
var vArray=new Array;vArray[0]=vDatePortion;vArray[1]=vTimePortion;return vArray;}
function validateChangedDatetime(pObject,pDisplayError)
{try
{if(typeof(pDisplayError)=="undefined"||pDisplayError==null)
{pDisplayError=true;}
if(typeof(pObject)=="undefined"||pObject==null)
{pObject=window.event.srcElement;}
var vValue=trim(pObject.value);var vDatePortion=vValue;var vTimePortion='';var vArray=new Array;vArray=splitDatetime(vValue);if(vArray==false)
{if(pDisplayError==true)
{pObject.prevValue="InputError"
soundError()
displayMessageOnField(pObject.id,MSG_ERR_FIELD_INVALID_DATE)
pObject.select();window.event.returnValue=false;return;}
else
{return MSG_ERR_FIELD_INVALID_DATE;}}
vDatePortion=vArray[0];vTimePortion=vArray[1];pObject.value=vDatePortion;var vDateValidated=validateChangedDate(pObject,pDisplayError,false);if(!vDateValidated||vDateValidated==MSG_ERR_FIELD_INVALID_DATE)
{return MSG_ERR_FIELD_INVALID_DATE;}
pObject.value=pObject.value+vTimePortion;if(pObject.custom_onblur)
{eval(pObject.custom_onblur)}
return true;}
catch(e)
{return MSG_ERR_FIELD_INVALID_DATE;}}
function datetimePlusMinus(dateField){datePlusMinus(dateField,true);}
function initWithTodaysDate(dateField,includeTime){var vSplitDate=new Object()
var vToday=null;try
{vToday=new Date(getOrgServerTime());}
catch(e)
{vToday=new Date();}
vSplitDate.year=vToday.getFullYear();vSplitDate.month=vToday.getMonth()+1;vSplitDate.day=vToday.getDate();var vDateValue=convertDateToClientFormat(vSplitDate);if(includeTime){var vTime=validateTime(vToday.getHours()+":"+vToday.getMinutes());dateField.value=trim(vDateValue+" "+vTime);}else{dateField.value=trim(vDateValue);}
window.event.returnValue=false;}
function calhover(obj){if(obj.className=="day"){obj.className="dayhover";}
else if(obj.className=="dayoff"){obj.className="dayoffhover";}
else if(obj.className=="dayhover"){obj.className="day";}
else if(obj.className=="dayoffhover"){obj.className="dayoff";}}
function multiplyNumber(pNumber1,pNumber2)
{var vTimes=10000000;var vResult=Math.round(pNumber1*pNumber2*vTimes)
vResult=vResult/vTimes;return vResult;}
function divideNumber(pNumber1,pNumber2)
{var vTimes=10000000;var vResult=Math.round((pNumber1/pNumber2)*vTimes)
return vResult/vTimes;}
function roundNumber(pNumber,pDecimals)
{return Math.round(pNumber*Math.pow(10,pDecimals))/Math.pow(10,pDecimals);}
function roundUp(pNumber)
{var rndNum=Math.round(pNumber,0);if(rndNum<pNumber)rndNum++;return rndNum;}
function validteNumberScale(pField,pFieldValue)
{if((isObject(pField.numbertype))&&(pField.numbertype!=""))
{if(!isObject(pFieldValue))
{pFieldValue=convertNbrToStdFormat(pField.value,pField.datatype);}
else
{pFieldValue=convertNbrToStdFormat(pFieldValue,pField.datatype);}
var vScalePercision=pField.numbertype.split(",");if(!isObject(vScalePercision[1]))
{vScalePercision[1]=0;}
var vLeftScale=parseInt(vScalePercision[0],10)-parseInt(vScalePercision[1],10);if(isObject(vLeftScale)&&vLeftScale>0)
{if(pFieldValue.substring(0,1)=='+'||pFieldValue.substring(0,1)=='-')
pFieldValue=pFieldValue.substring(1);var vDecIdx=pFieldValue.indexOf(".");if(vDecIdx>-1)
{pFieldValue=pFieldValue.substring(0,vDecIdx);}
if(pFieldValue.toString().length>vLeftScale)
{return false;}}}
else
{if(!isObject(pFieldValue))
{pFieldValue=convertNbrToStdFormat(pField.value,pField.datatype);}
else
{pFieldValue=convertNbrToStdFormat(pFieldValue,pField.datatype);}
if(pFieldValue.substring(0,1)=='+'||pFieldValue.substring(0,1)=='-')
pFieldValue=pFieldValue.substring(1);if(pFieldValue.indexOf(".")>-1)
{if(pFieldValue.length>39)
return false;}
else
{if(pFieldValue.length>38)
return false;}}
return true;}
function validateNumberChar(pObject)
{var vChar=String.fromCharCode(window.event.keyCode)
var vdatatype=pObject.datatype
if(!isObject(vdatatype))
vdatatype='number';var vPattern=new RegExp("[0123456789]");if(vdatatype=="currency"||vdatatype=="currencyfixed"||"currency".toUpperCase()==vdatatype||"currencyfixed".toUpperCase()==vdatatype)
{if(!vPattern.test(vChar)&&vChar!=UL.NEGSYMBOL&&vChar!=UL.CURRDECIMALSYMBOL&&vChar!=UL.CURRGROUPINGSYMBOL)
{event.returnValue=false;soundError();return;}}
else
{if(!vPattern.test(vChar)&&vChar!=UL.NEGSYMBOL&&vChar!=UL.DECIMALSYMBOL&&vChar!=UL.GROUPINGSYMBOL)
{event.returnValue=false;soundError();return;}}
if(vdatatype=="integer"&&vChar==UL.DECIMALSYMBOL)
{event.returnValue=false;soundError()
return;}
var decSym=UL.DECIMALSYMBOL
if(vdatatype=="currency"||vdatatype=="currencyfixed"||"currency".toUpperCase()==vdatatype||"currencyfixed".toUpperCase()==vdatatype)
{decSym=UL.CURRDECIMALSYMBOL;}
if(vChar==decSym)
{if(pObject.value.indexOf(decSym)!==-1)
{event.returnValue=false;soundError()
return;}}
if(vChar==UL.NEGSYMBOL)
{if(pObject.value.indexOf(UL.NEGSYMBOL)!==-1&&document.selection.createRange().text.indexOf(UL.NEGSYMBOL)==-1)
{event.returnValue=false;soundError()
return;}}}
function validateChangedNumber(pObject,pDisplayError)
{if(typeof(pDisplayError)=="undefined"||pDisplayError==null)
{pDisplayError=true;}
if(typeof(pObject)=="undefined"||pObject==null)
{pObject=window.event.srcElement;}
if(!fieldValueHasChanged(pObject))
{clearDisplayFieldMessage(pObject.id)
if(pObject.custom_onblur&&pObject.skip_customonblur!="true")
{eval(pObject.custom_onblur)}
return;}
clearDisplayFieldMessage(pObject.id)
var vStdFormat=convertNbrToStdFormat(pObject.value,pObject.datatype)
if(vStdFormat!==""&&!vStdFormat)
{if(pDisplayError==true)
{soundError()
displayMessageOnField(pObject.id,MSG_ERR_FIELD_INVALID_NUMBER)
pObject.select();event.returnValue=false;return;}
else
{return MSG_ERR_FIELD_INVALID_NUMBER;}}
if(!validteNumberScale(pObject))
{soundError();displayMessageOnField(pObject.id,MSG_ERR_FIELD_INVALID_NUMBER);pObject.select();event.returnValue=false;return;}
clearDisplayFieldMessage(pObject.id)
if(pObject.datatype=='number'&&pObject.precision>0)
{pObject.value=convertNbrToSpecialFormat(vStdFormat,pObject.precision);}
else
{pObject.value=convertNbrToClientFormat(vStdFormat,pObject.datatype)}
pObject.prevValue=pObject.value
setPageChanged(pObject)
if(pObject.custom_onblur&&pObject.skip_customonblur!="true")
{eval(pObject.custom_onblur)}}
function convertNbrToSpecialFormat(pStdFormat,pPrecision)
{var vNoOfZerosToTruncate=0;var vFormatted="";if(!isblank(pStdFormat))
{if((pPrecision>=0&&pPrecision<11))
{if(pStdFormat.lastIndexOf(".")>-1)
{var vNumberOfDecimal=pStdFormat.length-pStdFormat.lastIndexOf(".")-1;if(vNumberOfDecimal<pPrecision)
{pStdFormat=parseFloat(pStdFormat).toFixed(pPrecision);vNoOfZerosToTruncate=vNoOfZerosToTruncate-1;}
else if(vNumberOfDecimal>pPrecision)
{vNoOfZerosToTruncate=vNumberOfDecimal-pPrecision;}
else
{vNoOfZerosToTruncate=0;}}
else
{pStdFormat=parseFloat(pStdFormat).toFixed(pPrecision);vNoOfZerosToTruncate=vNoOfZerosToTruncate-1;}}
pStdFormat=pStdFormat.toString();while(pStdFormat.length>1&&pStdFormat.lastIndexOf(".")>-1&&pStdFormat.length-pStdFormat.lastIndexOf("0")==1&&pStdFormat.length-pStdFormat.lastIndexOf(".")>1&&vNoOfZerosToTruncate>0)
{pStdFormat=pStdFormat.substring(0,pStdFormat.length-1);vNoOfZerosToTruncate=vNoOfZerosToTruncate-1;}
if(pStdFormat.length>1&&pStdFormat.lastIndexOf(".")>-1&&pStdFormat.length-pStdFormat.lastIndexOf(".")==1)
{pStdFormat=pStdFormat.substring(0,pStdFormat.length-1);}
var vSplitNumber,vIntPart,vDecPart;vSplitNumber=pStdFormat.split(".");vIntPart=convertNbrToClientFormat(vSplitNumber[0],"number");vDecPart=(typeof vSplitNumber[1]!="undefined")?vSplitNumber[1]:"";if(isblank(vDecPart))
{return vIntPart;}
vFormatted=vIntPart+"."+vDecPart;}
return vFormatted;}
function convertNbrToClientFormat(pStdFormat,pDatatype)
{if(pStdFormat!=null)
pStdFormat=pStdFormat.toString();while(pStdFormat!=null&&pStdFormat.length>1&&pStdFormat.lastIndexOf(".")>-1&&pDatatype=="currency"&&pStdFormat.length-pStdFormat.lastIndexOf("0")==1&&pStdFormat.length-pStdFormat.lastIndexOf(".")>3)
{pStdFormat=pStdFormat.substring(0,pStdFormat.length-1);}
while(pStdFormat!=null&&pStdFormat.length>1&&pStdFormat.lastIndexOf(".")>-1&&pDatatype!="currency"&&pStdFormat.length-pStdFormat.lastIndexOf("0")==1&&pStdFormat.length-pStdFormat.lastIndexOf(".")>1)
{pStdFormat=pStdFormat.substring(0,pStdFormat.length-1);}
if(pStdFormat!=null&&pStdFormat.length>1&&pStdFormat.lastIndexOf(".")>-1&&pStdFormat.length-pStdFormat.lastIndexOf(".")==1)
{pStdFormat=pStdFormat.substring(0,pStdFormat.length-1);}
var vNegative=false;var vIndex;var vNbrDecimals=parseInt(UL.DIGITSAFTERDEC,10);if(pDatatype=="currency"||pDatatype=="currencyfixed"||"currency".toUpperCase()==pDatatype||"currencyfixed".toUpperCase()==pDatatype)
{vNbrDecimals=parseInt(UL.CURRDIGITSAFTERDEC,10);}
if(pStdFormat=="")return"";var vNumber=parseFloat(pStdFormat)
if(!isNaN(vNumber))
{var vPosMinus=pStdFormat.toString().toUpperCase().indexOf("E-");if(vPosMinus>=0)
{vNumber=1+vNumber;vNumber=vNumber.toString();pStdFormat=vNumber.substring(1);}}
if(pStdFormat.charAt(0)=='-')
{vNegative=true;pStdFormat=pStdFormat.substring(1)}
if(pStdFormat.charAt(0)=='+')
{vNegative=false;pStdFormat=pStdFormat.substring(1)}
var vSplitNumber=pStdFormat.split(".")
var vIntPart=vSplitNumber[0];var vDecPart=(typeof vSplitNumber[1]!="undefined")?vSplitNumber[1]:"";if((pDatatype=="integer"||"integer".toUpperCase()==pDatatype)&&vDecPart!=="")
{vValue=roundNumber(parseFloat(vIntPart+"."+vDecPart),0)
vSplitNumber=vValue.toString().split(".")
vIntPart=vSplitNumber[0];vDecPart="";}
if((pDatatype=="currencyfixed"||"currencyfixed".toUpperCase()==pDatatype)&&vDecPart!=="")
{vValue=roundNumber(parseFloat(vIntPart+"."+vDecPart),vNbrDecimals)
vSplitNumber=vValue.toString().split(".")
vIntPart=vSplitNumber[0];vDecPart=(typeof vSplitNumber[1]!="undefined")?vSplitNumber[1]:"";}
var vRemaining=vIntPart;var vIntFormatted="";var vDigitsInGroup=parseInt(UL.DIGITSINGROUP,10);if(pDatatype=="currency"||pDatatype=="currencyfixed"||"currency".toUpperCase()==pDatatype||"currencyfixed".toUpperCase()==pDatatype)
{vDigitsInGroup=parseInt(UL.CURRDIGITSINGROUP,10);}
while(true)
{if(vRemaining.length>vDigitsInGroup)
{if(pDatatype=="currency"||pDatatype=="currencyfixed"||"currency".toUpperCase()==pDatatype||"currencyfixed".toUpperCase()==pDatatype)
vIntFormatted=UL.CURRGROUPINGSYMBOL+vRemaining.slice(vRemaining.length-vDigitsInGroup)+vIntFormatted;else
vIntFormatted=UL.GROUPINGSYMBOL+vRemaining.slice(vRemaining.length-vDigitsInGroup)+vIntFormatted;vRemaining=vRemaining.slice(0,vRemaining.length-vDigitsInGroup);}
else
{vIntFormatted=vRemaining+vIntFormatted;vRemaining="";break;}}
if(pDatatype=="currency"||pDatatype=="currencyfixed"||"currency".toUpperCase()==pDatatype||"currencyfixed".toUpperCase()==pDatatype)
{while(vDecPart.length<vNbrDecimals)
{vDecPart+="0"}}
vFormattedValue=vIntFormatted
if(pDatatype=="currency"||pDatatype=="currencyfixed"||"currency".toUpperCase()==pDatatype||"currencyfixed".toUpperCase()==pDatatype)
{if(vDecPart!=="")vFormattedValue+=UL.CURRDECIMALSYMBOL+vDecPart;}
else
if(vDecPart!=="")vFormattedValue+=UL.DECIMALSYMBOL+vDecPart;if(vNegative)
{switch(parseInt(UL.NEGFORMAT,10))
{case 3:case 6:case 7:case 10:case 11:case 13:vFormattedValue=vFormattedValue+UL.NEGSYMBOL
default:vFormattedValue=UL.NEGSYMBOL+vFormattedValue}}
return vFormattedValue}
function convertNbrToStdFormat(pValue,pDatatype)
{if(!isObject(pDatatype))
pDatatype='number';var vNegative=false
var vIndex
pValue=trim(pValue)
if(pValue.length==0)return"";if(pValue.charAt(0)==UL.NEGSYMBOL)
{vNegative=true;pValue=pValue.substring(1)}
if(pValue.charAt(0)==UL.POSSYMBOL)
{pValue=pValue.substring(1)}
if(pValue.charAt(pValue.length-1)==UL.NEGSYMBOL)
{vNegative=true;pValue=pValue.substring(0,pValue.length-1)}
if(pValue.indexOf(UL.NEGSYMBOL)>-1)return false;if(pDatatype=="currency"||pDatatype=="currencyfixed"||"currency".toUpperCase()==pDatatype||"currencyfixed".toUpperCase()==pDatatype)
{if(UL.CURRGROUPINGSYMBOL!=="")
{while(true)
{vIndex=pValue.indexOf(UL.CURRGROUPINGSYMBOL)
if(vIndex<0)break;pValue=pValue.substring(0,vIndex)+pValue.substr(vIndex+1)}}}
else
{if(UL.GROUPINGSYMBOL!=="")
{while(true)
{vIndex=pValue.indexOf(UL.GROUPINGSYMBOL)
if(vIndex<0)break;pValue=pValue.substring(0,vIndex)+pValue.substr(vIndex+1)}}}
vIndex=0;vOccurs=0;if(pDatatype=="currency"||pDatatype=="currencyfixed"||"currency".toUpperCase()==pDatatype||"currencyfixed".toUpperCase()==pDatatype)
{while(true)
{vIndex=pValue.indexOf(UL.CURRDECIMALSYMBOL,vIndex)
if(vIndex<0)break;vOccurs++
pValue=pValue.substring(0,vIndex)+"."+pValue.substr(vIndex+1)
vIndex++}}
else
{while(true)
{vIndex=pValue.indexOf(UL.DECIMALSYMBOL,vIndex)
if(vIndex<0)break;vOccurs++
pValue=pValue.substring(0,vIndex)+"."+pValue.substr(vIndex+1)
vIndex++}}
if(vOccurs>1)return false;if(pValue.indexOf(".")==0)
pValue="0"+pValue
if(vNegative)pValue="-"+pValue;if(isNaN(pValue))
{return false;}
else
{return pValue;}}
function portletin(pTableRow){if(pTableRow.className!="gridrowselect")
{pTableRow.className="gridrowhover";}}
function portletout(pTableRow)
{if(pTableRow.className!="gridrowselect")
{pTableRow.className="gridrow";}}
function portletselect(pTableRow)
{var vTable=pTableRow.parentElement.parentElement;if(vTable.selectedRow)
{vTable.selectedRow.className="gridrow";}
vTable.selectedRow=pTableRow
pTableRow.className="gridrowselect";}
function showStatusMeter()
{var vStatusDiv=document.getElementById("statusmeter");var vGlassDiv=document.getElementById("glasstop");if(vStatusDiv)
{vStatusDiv.style.display="block";}
if(vGlassDiv)
{setglasstop();}}
function hideStatusMeter()
{var vStatusDiv=document.getElementById("statusmeter");var vGlassDiv=document.getElementById("glasstop");if(vStatusDiv)
{vStatusDiv.style.display="none";}
if(vGlassDiv)
{removeglasstop();}}
function processEventGridRefreshReselect(pSrcElemRef,pActionURL,pFieldsToProcessString,pValidate,pExpectedRow,pGridKeyNames,pGridKeyValues)
{var vGridHead=document.getElementById("gridhead_table");var vGridBody=document.getElementById("gridbody_table");vGridHead.gSelectRowOnInit=true;if(!isObject(pExpectedRow))
{pExpectedRow=vGridBody.gSelectedItemsArray[0];}
vGridHead.gSelectRowNum=pExpectedRow;if(isObject(pGridKeyNames))
{if(typeof(pGridKeyNames)!="string")
{pGridKeyNames=pGridKeyNames.toString();}
vGridHead.gSelectRowKeyNames=pGridKeyNames;}
if(isObject(pGridKeyValues))
{if(typeof(pGridKeyValues)!="string")
{pGridKeyValues=pGridKeyValues.toString();}
vGridHead.gSelectRowKeyValues=pGridKeyValues;}
if(isObject(pValidate)&&pValidate)
{processEventValidate(pSrcElemRef,pActionURL,pFieldsToProcessString)}
else
{processEvent(pSrcElemRef,pActionURL,pFieldsToProcessString)}}
function processEvent(pSrcElemRef,pActionURL,pFieldsToProcessString)
{if(pSrcElemRef.disabled)
return;if(isObject(window.event))
window.event.cancelBubble=true;gProcessEventSrcElemRef=pSrcElemRef;gProcessEventActionURL=pActionURL;gProcessEventFieldValueString=getNameValues(pFieldsToProcessString);var vWindow=getBrowserWindow();vWindow.showStatusMeter();gProcessEventTimer=window.setTimeout("processEventCore(gProcessEventSrcElemRef,gProcessEventActionURL,gProcessEventFieldValueString)",1);}
function processEventValidate(pSrcElemRef,pActionURL,pFieldsToProcessString)
{if(pSrcElemRef.disabled)
return;if(isObject(window.event))
window.event.cancelBubble=true;gProcessEventSrcElemRef=pSrcElemRef;gProcessEventActionURL=pActionURL;if(!checkRequiredPage()||checkAllTextAreaMaxLength())
{gProcessEventFoundStopError=true;return;}
gProcessEventFoundStopError=false;gPerform7iFunctionFoundError=false;gProcessEventFieldValueString=getNameValues(pFieldsToProcessString);var vWindow=getBrowserWindow();vWindow.showStatusMeter();gProcessEventTimer=window.setTimeout("processEventCore(gProcessEventSrcElemRef,gProcessEventActionURL,gProcessEventFieldValueString)",1);}
function postHtmlForm(formName,url)
{var reqForm=document.getElementById(formName);reqForm.method="POST";reqForm.action=url;reqForm.submit();}
function addProcessFormElement(nm,val,formName)
{reqForm=document.getElementById(formName)
if(typeof(reqForm)=="undefined"||reqForm==null)
{reqForm=document.createElement("form");reqForm.id=formName
document.documentElement.appendChild(reqForm);}
if(reqForm!=null)
{frmElm=document.createElement("input")
frmElm.type="hidden"
frmElm.name=nm
frmElm.id=nm
frmElm.value=val
reqForm.appendChild(frmElm)}}
function syncGridAfterUpdate()
{var browserWindow=getBrowserWindow();if(!gProcessEventFoundStopError&&gSyncGridTemp&&!isNaN(gLastGridAUFRowIndx)&&isObject(document.all('gridbody_table'))&&!gSyncGridAUFlag&&!browserWindow.messageBarOpen("confirm")&&!browserWindow.messageBarOpen("question")&&!browserWindow.messageBarOpen("error"))
{window.setTimeout("syncGridAfterUpdateTimer()",1);gSyncGridTemp=false;var vWindow=getBrowserWindow();vWindow.showStatusMeter();}
else
{gLastGridAUFRowIndx=null;gSyncGridTemp=false;}}
function syncGridAfterUpdateTimer()
{if(isObject(gLastGridAUFRowIndx))
{try
{findAndSelectRow(gLastGridAUFRowIndx,gGridKeyFieldsToSelect,gGridKeyValuesToSelect,true)}
catch(e)
{}}}
function isTabSaveUpdate()
{var tabSave=getInstallParam("INSTALL_TABSAVE");var isTabSave=false;if(!isObject(tabSave))
tabSave="INSERT";if(tabSave.toUpperCase()=="UPDATE")
isTabSave=true;return isTabSave}
function isTabSavePermissionSet()
{try
{var vAlterTabSave=false;var mainFrameWindow=getMainIframeWindow();var tabName=mainFrameWindow.gCurrentTab;if(tabName!=null&&isObject(tabName))
{var vTabsSource=mainFrameWindow.document.getElementById("tabs_display").XMLDocument;var vResult=vTabsSource.selectSingleNode("//TAB_ITEM/.[string(TABNAME)='"+tabName+"' and string(ALTERSAVE)='true']")
if(vResult!=null&&isObject(vResult))
{vAlterTabSave=true
return vAlterTabSave;}
vTabsSource=mainFrameWindow.document.getElementById("tabs_list").XMLDocument;vResult=vTabsSource.selectSingleNode("//TAB_ITEM/.[string(TABNAME)='"+tabName+"' and string(ALTERSAVE)='true']")
if(vResult!=null&&isObject(vResult))
{vAlterTabSave=true
return vAlterTabSave;}}
return vAlterTabSave;}
catch(exception)
{return false;}}
function processEventCore(pSrcElemRef,pActionURL,pFieldValueString,pSetXMLHTTPFlag,pLastValuesFlag,cacheArgs)
{if(cacheArgs==null||cacheArgs==true)
gLastExecutedParams=arguments;gProcessEventFoundStopError=false;gPerform7iFunctionFoundError=false;gProcessEventTimer="working";var vWindow=getBrowserWindow();if(isObject(vWindow.mainWindow)&&vWindow.mainWindow)
vWindow.hideStatusMeter();else
hideStatusMeter();if((isSaveAction||gProcessEventActionType=='save')&&isFieldMessagePresent())
{isSaveAction=false;gProcessEventActionType=null;return false;}
if(((isObject(gProcessEventActionType)&&gProcessEventActionType.toLowerCase()=="save")||getFieldValue('pageaction')=="SAVE"||getFieldValue('pageaction')=="save")&&isObject(eval("window.gGridMode"))&&gGridMode=="master-detail"&&isTabSaveUpdate()&&isTabSavePermissionSet()&&isUpdateMode())
{try
{gGridKeyFieldsToSelect=getGridKeyNames();gGridKeyValuesToSelect=getGridKeyValuesFromFieldValues(gGridKeyFieldsToSelect);}
catch(e){}
pFieldValueString+="&NUMBER_OF_ROWS_FIRST_RETURNED="+document.all('gridbody_table').rows.length;gSyncGridTemp=true;var gTable=document.all('gridbody_table')
gLastGridAUFRowIndx=parseInt(gTable.gSelectedItemsArray[0],10);if(isNaN(gLastGridAUFRowIndx))
{gLastGridAUFRowIndx=gTable.gLastRowNumClicked;gTable.gLastRowNumClicked=null;}
if(isNaN(gLastGridAUFRowIndx))
{gLastGridAUFRowIndx=null;gSyncGridTemp=false;}}
var vResponse=formSubmitXMLHTTP(pActionURL,pFieldValueString,pSetXMLHTTPFlag);if(typeof(vResponse)=='string'&&vResponse=="false")
{displayStandardMessageOnPage("","error",MSG_ERR_XMLHTTP_SERVER_COMMUNICATION);gProcessEventFoundStopError=true;gPerform7iFunctionFoundError=true;}
else if(typeof(vResponse)=='string'&&vResponse=="sessiontimeout")
{gProcessEventFoundStopError=false;gPerform7iFunctionFoundError=false;}
else
{if(vResponse[0]!=null&&vResponse[0].indexOf("text/xml")>-1)
{if(isObject(vResponse[2])&&vResponse[2]!="")
loadCachedHTMLReturn(vResponse,pSrcElemRef,true);else
loadProcessEventReturnXML(vResponse[1],null,pLastValuesFlag);}
else
{if(vResponse[0]!=null&&vResponse[0].indexOf("text/html")>-1)
{var vSendHTMLTo="";var vControlType="";if(typeof(pSrcElemRef.sendhtmlto)!="undefined")
{vSendHTMLTo=pSrcElemRef.sendhtmlto;}
if(typeof(pSrcElemRef.controltype)!="undefined")
{vControlType=pSrcElemRef.controltype;}
if(isObject(vResponse[2])&&vResponse[2]!="")
loadCachedHTMLReturn(vResponse,pSrcElemRef,true);else
loadProcessEventReturnHTML(vResponse[1],"",vSendHTMLTo,vControlType);}}}
vSendHTMLTo=null;vControlType=null;gProcessEventTimer=null;if(isHyperLinkWindow())
disableHyperLinkButtons();if(isObject(vResponse)&&isObject(vResponse[1]))
{return vResponse[1];}
else
{return false;}}
function loadCachedHTMLReturn(vResponse,pSrcElemRef,pDataOnly)
{var workwin=null;var vSendHTMLTo=pSrcElemRef.sendhtmlto;var vControlType=pSrcElemRef.controltype;if(typeof(vControlType)=="undefined"||vControlType=="")
{vControlType="current";}
if(typeof(vSendHTMLTo)=="undefined"||vSendHTMLTo=="")
{vSendHTMLTo="window";}
var cUrl="../htmls/"+trim(vResponse[2])
if(vSendHTMLTo=="window")
{if(window.gPageName=="LOGIN"||window.gPageName=="CLOGON")
{setglasstop();workwin=window.open(cUrl,"_blank","resizable=yes,menubar=no,scrollbars=yes,height="+
screen.availHeight+",width="+screen.availWidth);}
else
{location.replace(cUrl);}}
else
{switch(vControlType)
{case"modal":var vGlassDiv=document.getElementById("glasstop");if(vGlassDiv)
{setglasstop();}
break;case"current":vSendHTMLTo="window";break;default:break;}
obj=document.getElementById(vSendHTMLTo)
if(obj==null&&vSendHTMLTo=="SUBIFRAME")
obj=parent.document.getElementById(vSendHTMLTo);obj.src=cUrl;if(isObject(obj.elementstohide))
{for(var r=0;r<obj.elementstohide.length;r++)
{obj.elementstohide[r].style.display="none";}}
obj.style.display="block";}
if(pDataOnly)
{if(workwin!=null)
gFVForCachedHTML=vResponse[1];else
{var win=getBrowserWindow();win.gFVForCachedHTML=vResponse[1];}}}
function loadNewFVForCachedHTML()
{var vwin=null;if(window.mainWindow)
vwin=window.opener;else
vwin=getBrowserWindow();if(!isObject(vwin))
return;var newfv=vwin.gFVForCachedHTML;if(newfv!=null&&FIELDVALUES)
{loadProcessEventReturnXML(newfv,true,false,true);}
vwin.gFVForCachedHTML=null;}
function loadProcessEventReturnHTML(pResponseText,pReplaceFlag,pSendHTMLTo,pControlType)
{if(typeof(pReplaceFlag=="undefined")||pReplaceFlag=="")
{pReplaceFlag="_self";}
if(typeof(pControlType)=="undefined"||pControlType=="")
{pControlType="current";}
if(typeof(pSendHTMLTo)=="undefined"||pSendHTMLTo=="")
{pSendHTMLTo="window";}
key=trim(pResponseText)
var cUrl="";var loadExternalWeb=false;if(isObject(key)&&key.indexOf("<weburldata")==0)
{var vRoot=new ActiveXObject("Msxml.DOMDocument");vRoot.loadXML(key);cUrl=vRoot.getElementsByTagName("weburl")[0].text;cUrl=replaceStrs(cUrl);if(cUrl.indexOf("http://")==-1&&cUrl.indexOf("http:\\\\")==-1&&cUrl.indexOf("https://")==-1&&cUrl.indexOf("https:\\\\")==-1)
cUrl="http://"+cUrl;if("true"==vRoot.getElementsByTagName("newwindow")[0].text)
{window.open(cUrl,"_blank");return;}
else if("true"!=vRoot.getElementsByTagName("usertab")[0].text)
loadExternalWeb=true;}
else
{cUrl="COMMON?REQ_KEY="+key;document.cookie=cUrl+"; expires=Thu, 01-Jan-70 00:00:01 GMT;path=/";}
if(pSendHTMLTo=="window")
{if(window.gPageName=="LOGIN"||window.gPageName=="CLOGON")
{setglasstop();window.open(cUrl,"_blank","resizable=yes,menubar=no,scrollbars=yes,height="+
screen.availHeight+",width="+screen.availWidth);}
else
{location.replace(cUrl);}}
else
{switch(pControlType)
{case"modal":var vGlassDiv=document.getElementById("glasstop");if(vGlassDiv)
{setglasstop();}
break;case"current":pSendHTMLTo="window";break;default:break;}
obj=document.getElementById(pSendHTMLTo)
if(pSendHTMLTo=="MAINIFRAME")
{if(obj&&isObject(obj.contentWindow.gActiveSubIFrame))
{obj.contentWindow.gActiveSubIFrame.src="../includes/dummy.html";}}
if(obj==null&&pSendHTMLTo=="SUBIFRAME")
obj=parent.document.getElementById(pSendHTMLTo)
obj.src=cUrl;if(isObject(obj.elementstohide))
{for(var r=0;r<obj.elementstohide.length;r++)
{obj.elementstohide[r].style.display="none";}}
obj.style.display="block";}
if(loadExternalWeb)
{toggleMainPageStartCenter(false)
disableAllButtonsExceptBack();}}
function disableAllButtonsExceptBack()
{disableEnableToolbarButton(document.getElementById("NEW"),true);disableEnableToolbarButton(document.getElementById("DELETE"),true);disableEnableToolbarButton(document.getElementById("RESET"),true);disableEnableToolbarButton(document.getElementById("SAVE"),true);disableEnableToolbarButton(document.getElementById("PRINT"),true);disableEnableToolbarButton(document.getElementById("PREVIEW"),true);disableEnableToolbarButton(document.getElementById("NEXTROW"),true);disableEnableToolbarButton(document.getElementById("PREVROW"),true);disableEnableToolbarButton(document.getElementById("COPY"),true);disableEnableToolbarButton(document.getElementById("DESIGN"),true);disableEnableToolbarButton(document.getElementById("HELP"),true);disableEnableToolbarButton(document.getElementById("FORWARD"),true);}
function disableEnableToolbarButton(buttonObj,shouldDisable)
{if(!isObject(buttonObj)){return;}
var imageObj=buttonObj.getElementsByTagName("IMG")[0];var buttonState=(shouldDisable)?"disable":"out";var cursorIcon=(shouldDisable)?"auto":"hand";if(isObject(imageObj)&&isObject(imageObj.id)){if(imageObj.id.indexOf("NEXT")==-1&&imageObj.id.indexOf("PREV")==-1){parent.toolselect(buttonState,imageObj.id);}else{parent.reccountselect(buttonState,imageObj);}}
buttonObj.disabled=shouldDisable;buttonObj.style.cursor=cursorIcon;}
function loadProcessEventReturnXML(pResponseText,pUpdateParent,pLastValuesFlag,pReplaceFV)
{if(pUpdateParent==null)
pUpdateParent=true;if(gBypassUpdateParentWindow)
pUpdateParent=false;var vCurrentPageAction=getFieldValue("pageaction")
if(vCurrentPageAction.toLowerCase()=="save")
{gProcessEventActionType="save"}
utf_regexp=new RegExp("UTF-8","i");pResponseText=pResponseText.replace(utf_regexp,"UTF-16");var vRoot=new ActiveXObject("Msxml.DOMDocument");vRoot.loadXML(pResponseText);var eSignNode=vRoot.getElementsByTagName("openesignpopup")[0];if(eSignNode!=null)
{var esignValue=dstmShowModalDialog("ESIGNPOPUP",null,"status=no;dialogHeight:380px; dialogWidth:550px;resizable:No;dialogTop:'';dialogLeft:'';center:true ");if("CANCELED"!=esignValue&&esignValue!=null&esignValue!="")
{gProcessEventCoreResult=processEventCore(gLastExecutedParams[0],gLastExecutedParams[1],gLastExecutedParams[2]+"&"+esignValue,gLastExecutedParams[3],gLastExecutedParams[4],false);return gProcessEventCoreResult;}
else
{gProcessEventFoundStopError=true;gPerform7iFunctionFoundError=true;return;}}
var vWUD=vRoot.getElementsByTagName("weburldata")[0];if(vWUD)
{var vPassport=vRoot.getElementsByTagName("passport")[0];if(vPassport)
loadCognosReport(vRoot);}
var vGridNode=vRoot.getElementsByTagName("GRIDRESULT")[0];if(vGridNode)
{loadGridData(vGridNode);if(pReplaceFV&&isObject(gridbody_table))
gridbody_table.padData();}
var vStructureNode=vRoot.getElementsByTagName("TREESTRUCTURE")[0];if(vStructureNode)
{loadStructureData(vStructureNode);}
var vUserDefinedAreaNode=vRoot.getElementsByTagName("USERDEFINEDAREA")[0];if(vUserDefinedAreaNode&&isObject(window.doLoadUserDefinedArea))
{doLoadUserDefinedArea(vUserDefinedAreaNode);vUserDefinedAreaNode.parentNode.removeChild(vUserDefinedAreaNode);}
var vWOCalHours=vRoot.getElementsByTagName("WOSCHEDULECAL")[0];if(vWOCalHours)
{updateCalLabor(vWOCalHours);}
var vFieldValuesList=vRoot.getElementsByTagName("FIELDVALUES");var vFieldValuesNode=vFieldValuesList[0];if(vFieldValuesNode)
{if(pReplaceFV)
{var fvxml=vFieldValuesNode.xml;if(vFieldValuesList[1])
{var endpart1=fvxml.indexOf("</ROW>");var fvxml2=vFieldValuesList[1].xml;var startpart2=fvxml2.indexOf("<ROW>");var fvxml=fvxml.substring(0,endpart1)
+fvxml2.substring(startpart2+5);}
FIELDVALUES.loadXML(fvxml);}
else
{loadFieldsDataIslandFromString(vFieldValuesNode.xml,pLastValuesFlag)
if(pUpdateParent&&(eval("parent.changeDisplayFields"))!=null&&!gProcessEventFoundStopError)
parent.changeDisplayFields(vFieldValuesNode,false);}
if(isObject(window.doAfterLoadXMLFV))
doAfterLoadXMLFV(vRoot);}
else
{if(pReplaceFV&&isObject(window.FIELDVALUES))
{FIELDVALUES.loadXML("<ROW></ROW");}
if(vRoot!=null&&vRoot.getElementsByTagName("ctrl").length>0)
{elem=vRoot.documentElement
if(elem!=null)
{dbElm=vRoot.createElement("db");docElem=elem.cloneNode(true)
dbElm.appendChild(docElem);vRoot.replaceChild(dbElm,vRoot.documentElement)
if(main_data_island.documentElement)
{main_data_island.replaceChild(vRoot.documentElement,main_data_island.documentElement);}
else
{main_data_island.appendChild(vRoot.documentElement);}
if(typeof(g_main)!=="undefined")
{var vMenuDivs=document.getElementsByTagName("DIV");for(var x=vMenuDivs.length;x>0;x--)
{var vMenuItem=vMenuDivs[x-1];if(isObject(vMenuItem.id))
{if(vMenuItem.id.substring(0,3)=="HM_")
{vMenuItem.parentElement.removeChild(vMenuItem);}}}
g_main.on_load();}}}}
var vEditGridNode=vRoot.getElementsByTagName("GRIDREQUEST")[0];var bRemoveMsgNode=false;if(vEditGridNode)
{var vSpecialAttribute=vEditGridNode.getAttribute("specialupdatefieldcase");if(!isblank(vSpecialAttribute)&&vSpecialAttribute=="true")
{handleUpdateFieldESignatures(vEditGridNode);}
else
{bRemoveMsgNode=loadEditGridData(vEditGridNode);}}
var vFieldFocusNode=vRoot.getElementsByTagName("FIELDFOCUS")[0];if(vFieldFocusNode)
{var vFocusFieldId=null;var vFocusNodeList=vFieldFocusNode.getElementsByTagName("FOCUS");if(vFocusNodeList)
{vFocusFieldId=vFocusNodeList[vFocusNodeList.length-1].getAttribute("value");}
if(!isblank(vFocusFieldId))
{setFocusOnXMLReturnTimeout(vFocusFieldId);}}
var vFirstFieldNode=vRoot.getElementsByTagName("FIRSTENTERABLEFIELD")[0];if(vFirstFieldNode)
{var vFirstField=null;vFirstField=vFirstFieldNode.getAttribute("id");if(!isblank(vFirstField))
{gFirstFocusField=vFirstField;}}
var vMessagesNode=vRoot.getElementsByTagName("MESSAGE")[0];var vNewMessageIsland=new ActiveXObject("Msxml.DOMDocument");if(vMessagesNode&&!bRemoveMsgNode)
{vNewMessageIsland.loadXML(vMessagesNode.xml);var vRebuiltXml=validateAndRebuildPageMessageXML(vNewMessageIsland);sendMessageXmlToDataIsland(vRebuiltXml);var vMessageItems=vMessagesNode.getElementsByTagName("MESSAGE_ITEM");for(var x=vMessageItems.length;x>0;x--)
{var vCurrentItem=vMessageItems[x-1];var vCurrentItemAttributesNode=vCurrentItem.attributes;var vCurrentItemType=vCurrentItemAttributesNode.getNamedItem("type").value;var lowertype=vCurrentItemType.toLowerCase();if(lowertype=="error")
{gPerform7iFunctionFoundError=true;if(isSaveAction)
{gProcessEventFoundStopError=true;break;}}
else if(lowertype=="question")
gPerform7iFunctionFoundQuestion=true;}
vMessagesNode.parentNode.removeChild(vMessagesNode);}
if(gForceSyncFV||(isObject(gProcessEventActionType)&&(gProcessEventActionType.toLowerCase()=="save"||gProcessEventActionType=="scrollrow")))
{gForceSyncFV=false;var vDataIsland=document.getElementById("FIELDVALUES");if(isObject(vDataIsland))
{var vXmlDoc=vDataIsland.XMLDocument;if((!gProcessEventFoundStopError)&&(!gScrollPending))
{if(!isObject(gCurrentFieldValuesDataIsland))
{gCurrentFieldValuesDataIsland=vXmlDoc;}
if(!isObject(gCurrentFieldValues))
{gCurrentFieldValues=vXmlDoc.getElementsByTagName("ROW")[0];}
copyLastFieldValues();}}}
gProcessEventActionType=null;vRoot=null;vMessagesNode=null;vFieldValuesNode=null;isSaveAction=false;gBypassUpdateParentWindow=false;if(getHasDefaultValueFlag()=="true"&&gMainEventsLoaded)
{window.setTimeout("handleDefaultValues()",5);}}
function handleUpdateFieldESignatures(pRoot)
{var vRoot=new ActiveXObject("Msxml.DOMDocument");var vSuccessRowCount=0;vRoot.loadXML(pRoot.xml);var vArgs=new Array();vArgs.push(vRoot);vArgs.push(window);var vESignValue=dstmShowModalDialog("ESIGNPOPUPEDITGRID",vArgs,"status=no;dialogHeight:380px; dialogWidth:550px;resizable:No;dialogTop:'';dialogLeft:'';center:true ");if("CANCELED"!=vESignValue&&vESignValue!=null&vESignValue!="")
{var vValues="EDGRID_XMLSTR="+urlEncode(vESignValue[0]);processEventCore(this,"BSUPLV.submit",vValues);}
else
{gProcessEventFoundStopError=true;}}
function setFocusOnXMLReturnTimeout(vfieldid)
{if(document.readyState!="complete")
setTimeout("setFocusOnXMLReturnTimeout('"+vfieldid+"')",20)
setFieldFocus(vfieldid,true)}
function formSubmitXMLHTTP(pActionURL,pDataToSend,pSetXMLHTTPFlag)
{try
{parent.rememberValues()}
catch(e)
{}
if(pSetXMLHTTPFlag==null)
pSetXMLHTTPFlag=true;pDataToSend=appendSystemFunction(pDataToSend)
var oHTTP=new ActiveXObject("Microsoft.XMLHTTP");if(isObject(gProcessEventActionType)&&gProcessEventActionType.toLowerCase()=="save"&&isObject(eval("window.getChangeCustomFieldNames")))
{pDataToSend+="&CHECK_CF_CHANGEFLAG=true"
+"&CHANGEDCUSTOMFIELDS="+urlEncode(getChangeCustomFieldNames());}
try
{oHTTP.open("POST",pActionURL,false);oHTTP.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
oHTTP.setRequestHeader("Request-Source","XMLHTTP")
if(pSetXMLHTTPFlag)
oHTTP.setRequestHeader("Request-Type","XMLHTTP")
oHTTP.send(pDataToSend);}
catch(exception)
{oHTTP=null;return"false";}
var done=true
if(oHTTP.status!=200)
{oHTTP=null;return"false";}
else
{var vResponseTextArray=new Array();vResponseTextArray[0]=oHTTP.getResponseHeader("Content-Type");vResponseTextArray[1]=oHTTP.responseText;vResponseTextArray[2]=oHTTP.getResponseHeader("CachedHTML");var vSessionTimeout=oHTTP.getResponseHeader("SessionTimeout");if(vSessionTimeout!=null&&(vSessionTimeout=="yes"||vResponseTextArray[1]=="loadlogin"))
{setSystemLogout();return"sessiontimeout";}
var vServerLogoutRequest=oHTTP.getResponseHeader("ServerLogoutRequest");if(vServerLogoutRequest!=null&&vServerLogoutRequest=="yes")
{setSystemLogout();}}
oHTTP=null;return vResponseTextArray;}
function formSubmitXMLHTTPs(pActionURL,pDataToSend,pSetXMLHTTPFlag)
{if(pSetXMLHTTPFlag==null)
pSetXMLHTTPFlag=true;pDataToSend=appendSystemFunction(pDataToSend)
var oHTTP=new ActiveXObject("Microsoft.XMLHTTP");try
{oHTTP.open("POST",pActionURL,false);oHTTP.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
oHTTP.setRequestHeader("Request-Source","XMLHTTP")
if(pSetXMLHTTPFlag)
oHTTP.setRequestHeader("Request-Type","XMLHTTP")
oHTTP.send(pDataToSend);}
catch(exception)
{oHTTP=null;return"false";}
var done=true
if(oHTTP.status!=200)
{oHTTP=null;return"false";}
else
{var vResponseTextArray=new Array();vResponseTextArray[0]=oHTTP.getResponseHeader("Content-Type");vResponseTextArray[1]=oHTTP.responseText;var vSessionTimeout=oHTTP.getResponseHeader("SessionTimeout");if(vSessionTimeout!=null&&(vSessionTimeout=="yes"||vResponseTextArray[1]=="loadlogin"))
{setSystemLogout();return"sessiontimeout";}
var vServerLogoutRequest=oHTTP.getResponseHeader("ServerLogoutRequest");if(vServerLogoutRequest!=null&&vServerLogoutRequest=="yes")
{setSystemLogout();}}
oHTTP=null;return vResponseTextArray;}
function getNameValues(pStringList)
{if(pStringList=="")
{return"";}
var vValidationErrorFound=false;var vFieldNameArray;var vXmlDoc=document.getElementById("FIELDVALUES").XMLDocument;var vRoot=vXmlDoc.documentElement;var vFieldNodesRoot=vRoot.getElementsByTagName("ROW")[0];if(pStringList.toUpperCase()=="ALL")
{vFieldNameArray=new Array();for(var i=0;i<vFieldNodesRoot.childNodes.length;i++)
{if(vFieldNodesRoot.childNodes[i].nodeName!="BUTTONS"&&vFieldNodesRoot.childNodes[i].nodeName.indexOf("_options")==-1&&vFieldNodesRoot.childNodes[i].nodeName!="ATTRIBUTES")
{vFieldNameArray.push(vFieldNodesRoot.childNodes[i].nodeName);}}}
else
{vFieldNameArray=pStringList.split(",");}
var vFieldValueArray=new Array();for(var i=0;i<vFieldNameArray.length;i++)
{var vCurrentFieldValue;if(vFieldNameArray[i].indexOf("_list")==-1)
{var vCurrentField=document.getElementById(vFieldNameArray[i]);if(vCurrentField&&isObject(vCurrentField.datatype))
{if(vCurrentField.datatype!="time")
{vCurrentFieldValue=convertToStandardFormatByElementId(vFieldNameArray[i]);switch(vCurrentField.style.textTransform)
{case"uppercase":vCurrentFieldValue=vCurrentFieldValue.toUpperCase();break;case"lowercase":vCurrentFieldValue=vCurrentFieldValue.toLowerCase();break;}}
else
{vOldFieldValue=getFieldValue(vCurrentField.id)
vCurrentFieldValue=getTimeFieldValue(vCurrentField);if(vOldFieldValue!=vCurrentFieldValue)
{setFieldValue(vCurrentField.id,vCurrentFieldValue);}}}
else
{vCurrentFieldValue=vFieldNodesRoot.getElementsByTagName(vFieldNameArray[i])[0].text;}
vFieldValueArray.push(vFieldNameArray[i]+"="+urlEncode(vCurrentFieldValue));}
else
{vCurrentFieldValue=getNameValuesListFromLB(vFieldNodesRoot.getElementsByTagName(vFieldNameArray[i])[0]);vFieldValueArray.push(vFieldNameArray[i]+"="+vCurrentFieldValue);vCurrentFieldValue=getNameValuesSelectedFromLB(vFieldNodesRoot.getElementsByTagName(vFieldNameArray[i])[0]);vFieldValueArray.push(vFieldNameArray[i]+"_selected="+vCurrentFieldValue);}}
var vFieldValueString=vFieldValueArray.toString();while(vFieldValueString.indexOf(",")!=-1)
{vFieldValueString=vFieldValueString.replace(",","&");}
while(vFieldValueString.indexOf("-_-")!=-1)
{vFieldValueString=vFieldValueString.replace("-_-","#");}
vValidationErrorFound=null;vFieldNameArray=null;vXmlDoc=null;vRoot=null;vFieldNodesRoot=null;vFieldValueArray=null;return vFieldValueString;}
function getNameValuesListFromLB(pNode)
{var vValueNodesCollection=pNode.getElementsByTagName("value");var vReturnArray=new Array();for(var i=0;i<vValueNodesCollection.length;i++)
{if(vValueNodesCollection[i].text!="")
{vReturnArray.push(urlEncode(vValueNodesCollection[i].text));}}
var vReturnString=vReturnArray.toString();while(vReturnString.indexOf(",")!=-1)
{vReturnString=vReturnString.replace(",","-_-");}
return vReturnString;}
function getNameValuesSelectedFromLB(pNode)
{var vSelectedItemsArray=getSelectedValuesLB(pNode.nodeName);var vSelectedNodes=vSelectedItemsArray[3];var vReturnArray=new Array();for(var i=0;i<vSelectedNodes.length;i++)
{var currentOptionNode=pNode.childNodes.item(vSelectedNodes[i]);if(currentOptionNode.getElementsByTagName("value")[0].text!="")
{vReturnArray.push(urlEncode(currentOptionNode.getElementsByTagName("value")[0].text));}}
var vReturnString=vReturnArray.toString();while(vReturnString.indexOf(",")!=-1)
{vReturnString=vReturnString.replace(",","-_-");}
return vReturnString;}
function loadFieldsDataIslandFromString(pXmlString,pLastValuesFlag)
{var vXmlDoc=document.getElementById("FIELDVALUES");if(isObject(vXmlDoc))
vXmlDoc=vXmlDoc.XMLDocument;else return;var vRoot=vXmlDoc.documentElement;var vRootTemp=new ActiveXObject("Msxml.DOMDocument");vRootTemp.loadXML(pXmlString);var vFieldContainerNode=vRoot.getElementsByTagName("ROW")[0];var vFieldContainerNodeTemp=vRootTemp.getElementsByTagName("ROW")[0];gOptionCount=0;var bOptionNotCounted=true;for(var i=0;i<vFieldContainerNodeTemp.childNodes.length;i++)
{if(vFieldContainerNodeTemp.childNodes.item(i).nodeName!="ATTRIBUTES"&&vFieldContainerNodeTemp.childNodes.item(i).nodeName!="BUTTONS"&&vFieldContainerNodeTemp.childNodes.item(i).nodeName.indexOf("_options")==-1&&vFieldContainerNodeTemp.childNodes.item(i).nodeName.indexOf("_list")==-1)
{var vMatchingNode=vFieldContainerNode.getElementsByTagName(vFieldContainerNodeTemp.childNodes.item(i).nodeName)[0];var vText;if(vMatchingNode)
{if(vFieldContainerNodeTemp.childNodes.item(i).hasChildNodes)
{if(isObject(vFieldContainerNodeTemp.childNodes.item(i).childNodes.item(0))&&vFieldContainerNodeTemp.childNodes.item(i).childNodes.item(0).nodeType==3)
{vText=vFieldContainerNodeTemp.childNodes.item(i).childNodes.item(0).text;}
else
{vText="";}}
else
{vText="";}
vMatchingNode.text=convertClientFormatByElementId(vMatchingNode.nodeName,vText);checkCustomFieldValue(vMatchingNode);resetControlFieldsLastValidatedValue(vMatchingNode);}
else
{var vNewNode=vXmlDoc.createElement(vFieldContainerNodeTemp.childNodes.item(i).nodeName);if(isObject(vFieldContainerNodeTemp.childNodes.item(i).childNodes.item(0))&&vFieldContainerNodeTemp.childNodes.item(i).childNodes.item(0).nodeType==3)
{vText=vFieldContainerNodeTemp.childNodes.item(i).childNodes.item(0).text;}
else
{vText="";}
vNewNode.text=convertClientFormatByElementId(vNewNode.nodeName,vText);vFieldContainerNode.insertBefore(vNewNode,vFieldContainerNode.childNodes.item(0));checkCustomFieldValue(vNewNode);resetControlFieldsLastValidatedValue(vNewNode);}}
else
{if(bOptionNotCounted)
{var filterfields_temp=vFieldContainerNodeTemp.getElementsByTagName("filterfields_options");if(isObject(filterfields_temp)&&filterfields_temp.length>0)
{var filterfields_temp2=filterfields_temp[0].getElementsByTagName("option")
if(isObject(filterfields_temp2))
gOptionCount=filterfields_temp2.length;}
var dataspylist_temp=vFieldContainerNodeTemp.getElementsByTagName("dataspylist_options");if(isObject(dataspylist_temp)&&dataspylist_temp.length>0)
{var dataspylist_temp2=dataspylist_temp[0].getElementsByTagName("option")
if(isObject(dataspylist_temp2))
gOptionCount=gOptionCount+dataspylist_temp2.length;}
var clevel3_temp=vFieldContainerNodeTemp.getElementsByTagName("clevel3_options");if(isObject(clevel3_temp)&&clevel3_temp.length>0)
{var clevel3_temp2=clevel3_temp[0].getElementsByTagName("option")
if(isObject(clevel3_temp2))
gOptionCoount=gOptionCoount+clevel3_temp2.length;}
bOptionNotCounted=false;}
var vCurrentItemNode=vFieldContainerNodeTemp.childNodes.item(i);var vMatchingNode=vFieldContainerNode.getElementsByTagName(vFieldContainerNodeTemp.childNodes.item(i).nodeName)[0];if(vMatchingNode)
{if(vCurrentItemNode.nodeName.toLowerCase()!="attributes")
{var vNewNode=vCurrentItemNode.cloneNode(true);vFieldContainerNode.replaceChild(vNewNode,vMatchingNode);}
else
{var vAttributeRootNode=vMatchingNode;for(var t=0;t<vCurrentItemNode.childNodes.length;t++)
{var vCurrentAttributeItemNode=vCurrentItemNode.childNodes.item(t);setFieldAttribute(vCurrentAttributeItemNode.nodeName,vCurrentAttributeItemNode.text,null,false);}}}
else
{var vNewNode=vCurrentItemNode.cloneNode(true);vFieldContainerNode.appendChild(vNewNode);}}}
var refreshData=vFieldContainerNodeTemp.getElementsByTagName("refreshpagadata")[0];if(pLastValuesFlag==null)
{pLastValuesFlag=false;}
if((isObject(refreshData)&&"true"==refreshData.text)&&(!pLastValuesFlag))
{copyLastFieldValues();}
implementAttributesAll();var vXmlDoc=null;var vRoot=null;var vRootTemp=null;var vFieldContainerNode=null;var vFieldContainerNodeTemp=null;if(isObject(window.document.activeElement)&&window.document.activeElement.tagName.toLowerCase()=="input")
{window.document.activeElement.select();}}
function convertClientFormatByElementId(pElem,pValue)
{eval("var field = document.getElementById(\""+pElem+"\")");var vReturnValue=pValue;if(isObject(field)&&isObject(field.ddlbid))
{field.lastSelectedValue=pValue;}
if(pValue!="")
{if(!field)
{return vReturnValue;}
if(!field.datatype)
{return vReturnValue;}
else
{switch(field.datatype.toLowerCase())
{case"timeseparator":if(isObject(UL.TIMESEPARATOR)&&UL.TIMESEPARATOR!="")
{vReturnValue=UL.TIMESEPARATOR;}
else
{vReturnValue=pValue;}
break;case"date":if(pValue.substr(0,1)=="-"||pValue.substr(0,1)=="+")
{vReturnValue=pValue;}
else
{var vSplitDate=new Object();vSplitDate=splitYearMonthDay(pValue,true);vReturnValue=convertDateToClientFormat(vSplitDate);}
break;case"datetime":if(pValue.substr(0,1)=="-"||pValue.substr(0,1)=="+")
{vReturnValue=pValue;}
else
{vReturnValue=convertDatetimeToClientFormat(pValue);}
break;case"number":if(field.precision>0)
{vReturnValue=convertNbrToSpecialFormat(pValue,field.precision);}
else
{vReturnValue=convertNbrToClientFormat(pValue,field.datatype);}
break;case"integer":vReturnValue=convertNbrToClientFormat(pValue,field.datatype);break;case"currency":vReturnValue=convertNbrToClientFormat(pValue,field.datatype);break;case"currencyfixed":vReturnValue=convertNbrToClientFormat(pValue,field.datatype);break;case"duration":vReturnValue=convertDurationToClientFormat(pValue);break;}}}
return vReturnValue}
function convertClientFormatByElementDoc(field,pValue)
{var vReturnValue=pValue;if(isObject(field)&&isObject(field.datatype)&&isObject(pValue))
{switch(field.datatype.toLowerCase())
{case"timeseparator":if(isObject(UL.TIMESEPARATOR)&&UL.TIMESEPARATOR!="")
{vReturnValue=UL.TIMESEPARATOR;}
else
{vReturnValue=pValue;}
break;case"date":if(pValue.substr(0,1)=="-"||pValue.substr(0,1)=="+")
{vReturnValue=pValue;}
else
{var vSplitDate=new Object();vSplitDate=splitYearMonthDay(pValue,true);vReturnValue=convertDateToClientFormat(vSplitDate);}
break;case"datetime":if(pValue.substr(0,1)=="-"||pValue.substr(0,1)=="+")
{vReturnValue=pValue;}
else
{vReturnValue=convertDatetimeToClientFormat(pValue);}
break;case"number":if(field.precision>0)
{vReturnValue=convertNbrToSpecialFormat(pValue,field.precision);}
else
{vReturnValue=convertNbrToClientFormat(pValue,field.datatype);}
break;case"integer":vReturnValue=convertNbrToClientFormat(pValue,field.datatype);break;case"currency":vReturnValue=convertNbrToClientFormat(pValue,field.datatype);break;case"currencyfixed":vReturnValue=convertNbrToClientFormat(pValue,field.datatype);break;case"duration":vReturnValue=convertDurationToClientFormat(pValue);break;}}
return vReturnValue}
function convertToClientFormatAllFields()
{var vXmlDoc=document.getElementById("FIELDVALUES").XMLDocument;var vRoot=vXmlDoc.documentElement;if(isObject(vRoot))
{var vFieldContainerNode=vRoot.getElementsByTagName("ROW")[0];for(var i=0;i<vFieldContainerNode.childNodes.length;i++)
{var vCurrentNode=vFieldContainerNode.childNodes.item(i);if(vCurrentNode.nodeName!="BUTTONS"&&vCurrentNode.nodeName!="ATTRIBUTES"&&vCurrentNode.nodeName!="message"&&vCurrentNode.nodeName.indexOf("_options")==-1&&vCurrentNode.nodeName.indexOf("_list")==-1)
{vCurrentNode.text=convertClientFormatByElementId(vCurrentNode.nodeName,vCurrentNode.text);}}}}
function convertToStandardFormatByElementId(pElem)
{eval("var field = document.getElementById(\""+pElem+"\")");var vReturnValue="";var vRootXml=document.getElementById("FIELDVALUES");var vFieldNode=vRootXml.getElementsByTagName(pElem)[0];if(isObject(vFieldNode)&&vFieldNode.parentNode.nodeName=="ATTRIBUTES")
{var vFieldNode=vRootXml.getElementsByTagName(pElem)[1];}
if(isObject(vFieldNode))
{vReturnValue=vFieldNode.text;if(vFieldNode.text=="")
{return"";}}
if(!field.datatype)
{return vReturnValue;}
else
{var vValid=true;switch(field.datatype.toLowerCase())
{case"date":if(vFieldNode.text.substr(0,1)=="-"||vFieldNode.text.substr(0,1)=="+")
{vReturnValue=vFieldNode.text;}
else
{vValid=validateChangedDate(field,false,false);{if(typeof(vValid)=="string"&&vValid.toLowerCase()=="invalid date")
{vReturnValue=vFieldNode.text;}
else
{vReturnValue=convertDateToStdFormat(vFieldNode.text);}}}
break;case"timeseparator":vReturnValue=":";break;case"datetime":if(vFieldNode.text.substr(0,1)=="-"||vFieldNode.text.substr(0,1)=="+")
{vReturnValue=vFieldNode.text;}
else
{vValid=validateChangedDatetime(field,false);if(typeof(vValid)=="string"&&vValid.toLowerCase()=="invalid date")
{vReturnValue=vFieldNode.text;}
else
{vReturnValue=convertDatetimeToStdFormat(vFieldNode.text);}}
break;case"number":vReturnValue=convertNbrToStdFormat(vFieldNode.text);break;case"integer":vReturnValue=convertNbrToStdFormat(vFieldNode.text);break;case"currency":vReturnValue=convertNbrToStdFormat(vFieldNode.text,"currency");break;case"currencyfixed":vReturnValue=convertNbrToStdFormat(vFieldNode.text,"currencyfixed");break;case"duration":vReturnValue=convertDurationToStandardFormat(vFieldNode.text);break;}}
return vReturnValue}
function convertToStandardFormatByDatatype(vDatatype,vValue)
{var vReturnValue="";vReturnValue=vValue;switch(vDatatype.toLowerCase())
{case"date":if(vValue.substr(0,1)=="-"||vValue.substr(0,1)=="+")
{vReturnValue=vValue;}
else
{vReturnValue=convertDateToStdFormat(vValue);}
break;case"timeseparator":vReturnValue=":";break;case"datetime":if(vValue.substr(0,1)=="-"||vValue.substr(0,1)=="+")
{vReturnValue=vValue;}
else
{vReturnValue=convertDatetimeToStdFormat(vValue);}
break;case"number":vReturnValue=convertNbrToStdFormat(vValue);break;case"integer":vReturnValue=convertNbrToStdFormat(vValue);break;case"currency":vReturnValue=convertNbrToStdFormat(vValue,"currency");break;case"currencyfixed":vReturnValue=convertNbrToStdFormat(vValue,"currencyfixed");break;case"duration":vReturnValue=convertDurationToStandardFormat(vValue);break;}
return vReturnValue}
function convertToStandardFormatByElementIdDoc(vFieldNode)
{var vReturnValue="";if(isObject(vFieldNode)&&isObject(vFieldNode.datatype)&&isObject(vFieldNode.value)&&"INPUT"==vFieldNode.tagName)
{vReturnValue=vFieldNode.value;switch(vFieldNode.datatype.toLowerCase())
{case"date":if(vFieldNode.value.substr(0,1)=="-"||vFieldNode.value.substr(0,1)=="+")
{vReturnValue=vFieldNode.value;}
else
{vReturnValue=convertDateToStdFormat(vFieldNode.value);}
break;case"timeseparator":vReturnValue=":";break;case"datetime":if(vFieldNode.value.substr(0,1)=="-"||vFieldNode.value.substr(0,1)=="+")
{vReturnValue=vFieldNode.value;}
else
{vReturnValue=convertDatetimeToStdFormat(vFieldNode.value);}
break;case"number":vReturnValue=convertNbrToStdFormat(vFieldNode.value);break;case"decimal":vReturnValue=convertNbrToStdFormat(vFieldNode.value);break;case"integer":vReturnValue=convertNbrToStdFormat(vFieldNode.value);break;case"currency":vReturnValue=convertNbrToStdFormat(vFieldNode.value,"currency");break;case"currencyfixed":vReturnValue=convertNbrToStdFormat(vFieldNode.value,"currencyfixed");break;case"duration":vReturnValue=convertDurationToStandardFormat(vFieldNode.value);break;}}
return vReturnValue}
function changeFieldAttribute(pElement,pAttr)
{if(typeof(pElement)=="object")
{var vSourceElement=pElement.id;}
var vXmlDoc=document.getElementById("FIELDVALUES").XMLDocument;var vRoot=vXmlDoc;var vAttributeNode=vRoot.getElementsByTagName("ATTRIBUTES")[0];var vAttributeToChange=vAttributeNode.getElementsByTagName(pElement)[0];if(typeof(vAttributeToChange)!='undefined'&&vAttributeToChange!=null)
{vAttributeToChange.text=pAttr;}
else
{var vNewElement=vRoot.createElement(pElement);vAttributeNode.appendChild(vNewElement);}
implementAttributesByElementId(pElement,pAttr)
hideShowBlocks();}
function implementAttributesAll()
{if(typeof(gIsHeaderFieldContainer)!="undefined"&&gIsHeaderFieldContainer)
{return;}
var vXmlDoc=document.getElementById("FIELDVALUES").XMLDocument;var vRoot=vXmlDoc.documentElement;var vFieldContainerNode=vRoot.getElementsByTagName("ATTRIBUTES")[0];var vPageMode=getPageMode();if(vFieldContainerNode!=null)
{for(var i=0;i<vFieldContainerNode.childNodes.length;i++)
{var vElem=vFieldContainerNode.childNodes.item(i).nodeName;if((vElem.indexOf("_HH")==-1&&vElem.indexOf("_MI")==-1)||isCustomField(vElem))
{implementAttributesByElementId(vElem,vFieldContainerNode.childNodes.item(i).text,vPageMode);var vField=document.getElementById(vElem);if(isObject(vField))
{if(isObject(vField.datatype)&&vField.datatype=="time")
{implementAttributesByElementId(vElem+"_HH",vFieldContainerNode.childNodes.item(i).text,vPageMode);implementAttributesByElementId(vElem+"_MI",vFieldContainerNode.childNodes.item(i).text,vPageMode);}}}}}
hideShowBlocks();}
function getPageMode()
{var vPageMode=getFieldValue("pagemode").toLowerCase();try
{vMainIframRefObj=getMainIframeWindow();if(vMainIframRefObj.gDesignerPreviewMode)
vPageMode="previewmode";}
catch(e)
{vWindow=getBrowserWindow();if(eval("vWindow.designerSelectPopup")!=null&&vWindow.designerSelectPopup)
{if(vWindow.gDesignerPreviewMode)
{vPageMode="previewmode";}}
else
{vPageMode="";}}
return vPageMode;}
function implementAttributesByElementId_normal(pAttr,vPageMode,vField)
{if(!isObject(vField))return;pElem=vField.id;var vParentCell=vField.parentElement;var vFieldGroup;var vLabel;var vFieldImage;var vDisplayField;var vClassRequired;var vClassOptional;var vClassProtected;var isLayoutDefined;var vFieldDisabled;var vFieldClass;var vTabStop;var vImageVisibility;var vAllFieldsInGroupAreHidden;var isOldField=false;if(vField.tagName=="A"||vField.datatype=="link")
{vClassRequired="link";vClassOptional="link";vClassProtected="form";}
else
{vClassRequired="form-required";vClassOptional="form";if(vField.datatype=="button")
{vClassProtected="form";}
else
{vClassProtected="form-readonly";}}
if(typeof(vParentCell)=="undefined"||vParentCell==null)
return;else if(vParentCell.parentElement.className=="layout-dummy")
{vFieldGroup=vParentCell.parentElement.parentElement.parentElement.parentElement.parentElement;isLayoutDefined=true;}
else
{if(window.name=="MAINIFRAME")
{vFieldGroup=vParentCell.parentElement;isOldField=true;}
else
{vFieldGroup=vParentCell;}
isLayoutDefined=false;}
if(vField.datatype=="button")
{vFieldGroup=vParentCell;vLabel="none";vFieldImage="none";vDisplayField="none";}
else
{if(isLayoutDefined&&vFieldGroup.fieldid==vField.id)
vLabel=vFieldGroup.firstChild;else
vLabel="none";if(isOldField)
{vLabel=vFieldGroup.firstChild;}
if(vLabel==null)
vLabel="none";else
{if(vLabel.className!="fieldlabel"&&vLabel.className!="screenDesignerFieldlabelNotInJsp"&&vLabel.className!="screenDesignerHidden_label")
vLabel="none";}
if(vField.datatype=="select"||vField.datatype=="timehour"||vField.datatype=="timeminute")
{vFieldImage=document.getElementById(pElem+'_arrow');vDisplayField=document.getElementById(pElem+'_display');}
else
{vFieldImage=document.getElementById(pElem+'_lu');vDisplayField="none";}
if(vFieldImage==null)vFieldImage="none";if(vDisplayField==null)vDisplayField="none";}
if(!isObject(pAttr)||pAttr=="")
{pAttr="optional";}
switch(pAttr)
{case"required":vFieldDisabled=false;vFieldClass=vClassRequired;vTabStop=true;vImageVisibility="visible";break;case"optional":vFieldDisabled=false;vFieldClass=vClassOptional;vTabStop=true;vImageVisibility="visible";break;case"protected":vFieldDisabled=true;vFieldClass=vClassProtected;vTabStop=false;vImageVisibility="hidden";break;case"hidden":vParentCell.style.display="none";if(vLabel!="none")
{vLabel.style.display="none";}
if(!isLayoutDefined)
vAllFieldsInGroupAreHidden=false;else
{vAllFieldsInGroupAreHidden=true;var pLen=vParentCell.parentElement.children.length;var chlElement=vParentCell.parentElement.children;for(i=0;i<pLen;i++)
{if(chlElement[i].style.display==null||chlElement[i].style.display==""||chlElement[i].style.display=="block")
{vAllFieldsInGroupAreHidden=false;break;}}}
if(vAllFieldsInGroupAreHidden)
{vFieldGroup.style.display="none";}
return;}
vFieldGroup.style.display="block";vParentCell.style.display="block";vField.disabled=vFieldDisabled;var descriptionField=vField;if(vDisplayField!="none")
descriptionField=vDisplayField;if(pAttr=="protected")
{if((descriptionField.datatype!="link"&&descriptionField.type!="radio"&&descriptionField.type!="checkbox")&&(!isObject(eval("descriptionField.onkeyup"))||(isObject(eval("descriptionField.onkeyup"))&&descriptionField.onkeyup.toString().indexOf("displayLookupOnKey")==-1)||((isObject(eval("descriptionField.onkeyup"))&&descriptionField.onkeyup.toString().indexOf("displayLookupOnKey")!=-1)&&(!isObject(eval("descriptionField.onvalidate"))||(isObject(eval("descriptionField.onvalidate"))&&descriptionField.onvalidate.toString()!="true")))))
{descriptionField.attachEvent("onmouseover",showValue);descriptionField.valueFieldID=descriptionField.id;descriptionField.attachEvent("onmouseout",hidedesc);descriptionField.parentElement.attachEvent("onmouseover",showValue);descriptionField.parentElement.valueFieldID=descriptionField.id;descriptionField.parentElement.attachEvent("onmouseout",hidedesc);}
descriptionField.ondblclick=invokeScreen;descriptionField.parentElement.ondblclick=invokeScreen;}
else
{descriptionField.detachEvent("onmouseover",showValue);descriptionField.valueFieldID=null;vField.detachEvent("onmouseout",hidedesc);descriptionField.parentElement.detachEvent("onmouseover",showValue);descriptionField.parentElement.valueFieldID=null;descriptionField.parentElement.detachEvent("onmouseout",hidedesc);descriptionField.ondblclick=null;descriptionField.parentElement.ondblclick=null;}
if(vField.datatype!="link"&&vField.datatype!="select"&&vField.datatype!="checkbox"&&vField.datatype!="time"&&vField.datatype!="radio")
{if(vField.ondblclick==null)
vField.ondblclick=invokeScreen;}
else
vField.ondblclick=null;if(pAttr=="required")
{vField.attachEvent("onblur",requiredFieldBlurCheck);}
if(vField.datatype!="time")
{vField.className=vFieldClass;}
vField.tabStop=vTabStop;if(vLabel!="none")
{vLabel.style.display="block";}
if(vDisplayField!="none")
{vDisplayField.disabled=vFieldDisabled;vDisplayField.className=vFieldClass;}
if(vFieldImage!="none")
{vFieldImage.style.visibility=vImageVisibility;}}
function implementAttributesByElementId(pElem,pAttr,vPageMode)
{var vField;var vInJsp=true;if(typeof(pElem)=="string")
{vField=document.getElementById(pElem);}
else
{vField=pElem;pElem=vField.id;}
if(!isObject(vField))
{return;}
var vIsLayoutHidden=false;if(!isObject(vPageMode))
vPageMode=getPageMode();if((vPageMode=="editlayout"||vPageMode=="previewmode")&&document.getElementById("LAYOUTMETA")!=null)
{var vSystemAttr=getScreenDesignerSystemAttribute(pElem);var vJspAttr=getScreenDesignerPresentInJspAttribute(pElem);if(vPageMode=="editlayout")
{if(vJspAttr.toLowerCase()=="n")
{vInJsp=false;pAttr=vSystemAttr;}
if(pAttr=="hidden")
{pAttr=vSystemAttr;if(vSystemAttr.toLowerCase()=="hidden")
{pAttr="optional";}
vIsLayoutHidden=true;}
if(pAttr=="systemrequired")
{pAttr="required";}
if(pAttr=="editionhidden")
{pAttr="hidden";}}
else
{if(vJspAttr.toLowerCase()=="n")
{pAttr="hidden";}}}
var vParentCell=vField.parentElement;var vFieldGroup;var vLabel;var vFieldImage;var vDisplayField;var vClassRequired;var vClassOptional;var vClassProtected;var isLayoutDefined;var vFieldDisabled;var vFieldClass;var vTabStop;var vImageVisibility;var vAllFieldsInGroupAreHidden;var isOldField=false;if(vField.tagName=="A"||vField.datatype=="link")
{vClassRequired="link";vClassOptional="link";vClassProtected="form";}
else
{vClassRequired="form-required";vClassOptional="form";if(vField.datatype=="button")
{vClassProtected="form";}
else
{vClassProtected="form-readonly";}}
if(typeof(vParentCell)=="undefined"||vParentCell==null)
return;else if(vParentCell.parentElement.className=="layout-dummy")
{vFieldGroup=vParentCell.parentElement.parentElement.parentElement.parentElement.parentElement;isLayoutDefined=true;}
else
{if(window.name=="MAINIFRAME")
{vFieldGroup=vParentCell.parentElement;isOldField=true;}
else
{vFieldGroup=vParentCell;}
isLayoutDefined=false;}
if(vField.datatype=="button")
{vFieldGroup=vParentCell;vLabel="none";vFieldImage="none";vDisplayField="none";}
else
{if(isLayoutDefined&&vFieldGroup.fieldid==vField.id)
vLabel=vFieldGroup.firstChild;else
vLabel="none";if(isOldField)
{vLabel=vFieldGroup.firstChild;}
if(vLabel==null)
vLabel="none";else
{if(vLabel.className!="fieldlabel"&&vLabel.className!="screenDesignerFieldlabelNotInJsp"&&vLabel.className!="screenDesignerHidden_label")
vLabel="none";}
if(vField.datatype=="select"||vField.datatype=="timehour"||vField.datatype=="timeminute")
{vFieldImage=document.getElementById(pElem+'_arrow');vDisplayField=document.getElementById(pElem+'_display');}
else
{vFieldImage=document.getElementById(pElem+'_lu');vDisplayField="none";}
if(vFieldImage==null)vFieldImage="none";if(vDisplayField==null)vDisplayField="none";}
if(!isObject(pAttr)||pAttr=="")
{pAttr="optional";}
if(vPageMode=="editlayout")
{vFieldGroup.className="";if(vLabel!="none")
{vLabel.className="fieldlabel";}
vParentCell.className="field-parent";}
switch(pAttr)
{case"required":vFieldDisabled=false;vFieldClass=vClassRequired;vTabStop=true;vImageVisibility="visible";break;case"optional":vFieldDisabled=false;vFieldClass=vClassOptional;vTabStop=true;vImageVisibility="visible";break;case"protected":vFieldDisabled=true;vFieldClass=vClassProtected;vTabStop=false;vImageVisibility="hidden";break;case"hidden":vParentCell.style.display="none";if(vLabel!="none")
{vLabel.style.display="none";}
if(!isLayoutDefined)
vAllFieldsInGroupAreHidden=false;else
{vAllFieldsInGroupAreHidden=true;for(i=0;i<vParentCell.parentElement.children.length;i++)
{if(vParentCell.parentElement.children[i].style.display==null||vParentCell.parentElement.children[i].style.display==""||vParentCell.parentElement.children[i].style.display=="block")
{vAllFieldsInGroupAreHidden=false;break;}}}
if(vAllFieldsInGroupAreHidden)
{vFieldGroup.style.display="none";}
return;}
vFieldGroup.style.display="block";vParentCell.style.display="block";vField.disabled=vFieldDisabled;var descriptionField=vField;if(vDisplayField!="none")
descriptionField=vDisplayField;if(pAttr=="protected"&&vPageMode!="editlayout"&&vPageMode!="previewmode")
{if((descriptionField.datatype!="link"&&descriptionField.type!="radio"&&descriptionField.type!="checkbox")&&(!isObject(eval("descriptionField.onkeyup"))||(isObject(eval("descriptionField.onkeyup"))&&descriptionField.onkeyup.toString().indexOf("displayLookupOnKey")==-1)||((isObject(eval("descriptionField.onkeyup"))&&descriptionField.onkeyup.toString().indexOf("displayLookupOnKey")!=-1)&&(!isObject(eval("descriptionField.onvalidate"))||(isObject(eval("descriptionField.onvalidate"))&&descriptionField.onvalidate.toString()!="true")))))
{descriptionField.attachEvent("onmouseover",showValue);descriptionField.valueFieldID=descriptionField.id;descriptionField.attachEvent("onmouseout",hidedesc);descriptionField.parentElement.attachEvent("onmouseover",showValue);descriptionField.parentElement.valueFieldID=descriptionField.id;descriptionField.parentElement.attachEvent("onmouseout",hidedesc);}
descriptionField.ondblclick=invokeScreen;descriptionField.parentElement.ondblclick=invokeScreen;}
else
{descriptionField.detachEvent("onmouseover",showValue);descriptionField.valueFieldID=null;vField.detachEvent("onmouseout",hidedesc);descriptionField.parentElement.detachEvent("onmouseover",showValue);descriptionField.parentElement.valueFieldID=null;descriptionField.parentElement.detachEvent("onmouseout",hidedesc);descriptionField.ondblclick=null;descriptionField.parentElement.ondblclick=null;}
if(vField.datatype!="link"&&vField.datatype!="select"&&vField.datatype!="checkbox"&&vField.datatype!="time"&&vField.datatype!="radio"&&vField.datatype!="button"&&vPageMode!="editlayout"&&vPageMode!="previewmode")
{if(vField.ondblclick==null)
vField.ondblclick=invokeScreen;}
else
vField.ondblclick=null;if(pAttr=="required"&&vPageMode!="editlayout"&&vPageMode!="previewmode")
{vField.attachEvent("onblur",requiredFieldBlurCheck);}
if(vField.datatype!="time")
{vField.className=vFieldClass;}
vField.tabStop=vTabStop;if(vLabel!="none")
{vLabel.style.display="block";}
if(vDisplayField!="none")
{vDisplayField.disabled=vFieldDisabled;vDisplayField.className=vFieldClass;}
if(vFieldImage!="none")
{vFieldImage.style.visibility=vImageVisibility;}
if(vInJsp&&vIsLayoutHidden&&vPageMode=="editlayout")
{vParentCell.className="screenDesignerHidden";if(vLabel!="none")
{vLabel.className="screenDesignerHidden_label";}
if(!isLayoutDefined)
vAllFieldsInGroupAreHidden=false;else
{vAllFieldsInGroupAreHidden=true;for(i=0;i<vParentCell.parentElement.children.length;i++)
{if(vParentCell.parentElement.children[i].className==null||vParentCell.parentElement.children[i].className==""||vParentCell.parentElement.children[i].className!="screenDesignerHidden")
{vAllFieldsInGroupAreHidden=false;break;}}}
if(vAllFieldsInGroupAreHidden)
{vFieldGroup.className="screenDesignerHidden";}}
if(!vInJsp&&vPageMode=="editlayout")
{vParentCell.className="screenDesignerFieldNotInJsp";if(vLabel!="none")
{vLabel.className="screenDesignerFieldlabelNotInJsp";}
if(!isLayoutDefined)
vAllFieldsInGroupAreHidden=false;else
{vAllFieldsInGroupAreHidden=true;for(i=0;i<vParentCell.parentElement.children.length;i++)
{if(vParentCell.parentElement.children[i].className==null||vParentCell.parentElement.children[i].className==""||vParentCell.parentElement.children[i].className!="screenDesignerFieldNotInJsp")
{vAllFieldsInGroupAreHidden=false;break;}}}
if(vAllFieldsInGroupAreHidden)
{vFieldGroup.className="screenDesignerFieldNotInJsp";}}}
function initFields2()
{var resizefn=document.body.onresize;document.body.onresize="";var vRoot=document.getElementById("FIELDVALUES").XMLDocument;if(vRoot.documentElement!=null&&vRoot.documentElement.tagName=="REMOVED_FORCACHE")
{gRefreshCachedHTMLData=true;loadNewFVForCachedHTML();}
var vRootDoc=vRoot.documentElement;var vFieldContainerNode=vRootDoc.getElementsByTagName("ROW")[0];for(var i=0;i<vFieldContainerNode.childNodes.length;i++)
{var vCurrentNode=vFieldContainerNode.childNodes.item(i);if(vCurrentNode.text!=''&&vCurrentNode.nodeName!="BUTTONS"&&vCurrentNode.nodeName!="ATTRIBUTES"&&vCurrentNode.nodeName!="message"&&vCurrentNode.nodeName.indexOf("_options")==-1&&vCurrentNode.nodeName.indexOf("_list")==-1)
{vCurrentNode.text=convertClientFormatByElementId(vCurrentNode.nodeName,vCurrentNode.text);}}
gCurrentFieldValuesDataIsland=vRoot;gCurrentFieldValues=vRoot.getElementsByTagName("ROW")[0];copyLastFieldValues();if(typeof(gIsHeaderFieldContainer)=="undefined"||!gIsHeaderFieldContainer)
{var vFieldContainerNode=vRoot.getElementsByTagName("ATTRIBUTES")[0];var vPageMode=getPageMode();if(vFieldContainerNode!=null)
{for(var i=0;i<vFieldContainerNode.childNodes.length;i++)
{var vElem=vFieldContainerNode.childNodes.item(i).nodeName;if((vElem.indexOf("_HH")==-1&&vElem.indexOf("_MI")==-1)||isCustomField(vElem))
{var vField=document.getElementById(vElem);if(vPageMode=="editlayout"||vPageMode=="previewmode")
implementAttributesByElementId(vElem,vFieldContainerNode.childNodes.item(i).text,vPageMode,vField);else
implementAttributesByElementId_normal(vFieldContainerNode.childNodes.item(i).text,vPageMode,vField);if(isObject(vField))
{if(isObject(vField.datatype)&&vField.datatype=="time")
{implementAttributesByElementId(vElem+"_HH",vFieldContainerNode.childNodes.item(i).text,vPageMode);implementAttributesByElementId(vElem+"_MI",vFieldContainerNode.childNodes.item(i).text,vPageMode);}}}}}
hideShowBlocks();}
if(gRefreshCachedHTMLData)
{gRefreshCachedHTMLData=false;checkAllCustomFieldValues();setTimeout("setFieldFocus('"+gFocusField+"',false)",500);var vFocusFieldId=document.getElementById(gFocusField);if(isObject(vFocusFieldId))
vFocusFieldId.value=getFieldValue(gFocusField);}
if(getFieldValue("isthiscopyrecord")=="true")
{setTimeout("postCopyRecordProcessing()",500);}
document.body.onresize=resizefn;}
function getHasDefaultValueFlag()
{var defaultValue=FIELDVALUES.selectSingleNode("//hasdefaultvalues");if(defaultValue!=null&&!isblank(defaultValue.text))
{return defaultValue.text;}
else
{return"false";}}
function setHasDefaultValueFlag(pValue)
{var defaultValue=FIELDVALUES.selectSingleNode("//hasdefaultvalues");if(defaultValue!=null)
{defaultValue.text=pValue;}
syncFieldValue("hasdefaultvalues",pValue);}
function handleDefaultValues()
{if(isObject(gProcessEventTimer))
{window.setTimeout("handleDefaultValues()",5);return;}
else
{var vDefaultField;var vFieldAttribute;var vNumFieldsToDefault=-1;gDefaultFieldsToValidate=new Array();var vDefaultValues=FIELDVALUES.XMLDocument.selectNodes("/FIELDVALUES/ROW/ATTRIBUTES/*/.[@defaultValue='true']");if(vDefaultValues!=null)
{for(var i=0;i<vDefaultValues.length;i++)
{vFieldAttribute=getFieldAttribute(vDefaultValues[i].nodeName);if(vFieldAttribute=="optional"||vFieldAttribute=="required")
{gDefaultFieldsToValidate.push(vDefaultValues[i].nodeName);}}}
if(gDefaultFieldsToValidate.length>=1)
{gDefaultValueValidation=true;window.setTimeout("validateDefaultValues()",1);}
else
{gDefaultFieldsToValidate=null;}
setHasDefaultValueFlag("false");}}
function validateDefaultValues()
{if(isObject(gProcessEventTimer))
{window.setTimeout("validateDefaultValues()",5);return;}
else
{if(gDefaultFieldsToValidate!=null)
{if(gDefaultFieldsToValidate.length==0)
{gDefaultFieldsToValidate=null;gDefaultValueValidation=false;copyLastFieldValues();}
else
{var vDefaultField;var vDataType;while(gDefaultFieldsToValidate.length>0)
{vDefaultField=document.getElementById(gDefaultFieldsToValidate.pop());vDataType=vDefaultField.datatype;vDefaultField.lastValidatedValue="";if(vDefaultField.onblur!=null)
{if((isObject(vDefaultField.onvalidate)&&vDefaultField.onvalidate=="true")||(vDataType=="number"||vDataType=="integer"||vDataType=="currency"||vDataType=="date"||vDataType=="datetime"))
{vDefaultField.value=getFieldValue(vDefaultField.id);}
vDefaultField.onblur();}
else if(vDataType=="select")
{gTableObjectDDLB=document.getElementById(vDefaultField.id+"_table");vDefaultField.value=getFieldValue(vDefaultField.id);vDefaultField=document.getElementById(vDefaultField.id+"_display");vDefaultField.value=getFieldValue(vDefaultField.id);if(isObject(vDefaultField))
{gCurrentCodeFieldDDLB=document.getElementById(vDefaultField.codefieldid);vDefaultField.lastSelectedValue="";vDefaultField.ondeactivate(vDefaultField);}}
else if(vDataType=="checkbox"&&vDefaultField.onclick!=null)
{vDefaultField.onclick();}
else
{vDefaultField.fireEvent("onblur");}
if(isObject(gProcessEventTimer))
{timerVariable=window.setTimeout("validateDefaultValues()",1);return;}}
gDefaultFieldsToValidate=null;gDefaultValueValidation=false;copyLastFieldValues();}}}}
function postCopyRecordProcessing()
{setFieldValue("forcingchange","true");if(isObject(eval("window.resetCustomFieldLastValues")))
resetCustomFieldLastValues();if(isObject(eval("window.resetLastValidatedValues")))
resetLastValidatedValues();}
function initFields()
{if(document.getElementById("FIELDVALUES").ondatasetcomplete!="")
{var vRoot=document.getElementById("FIELDVALUES").XMLDocument;if(vRoot.documentElement!=null&&vRoot.documentElement.tagName=="REMOVED_FORCACHE")
{gRefreshCachedHTMLData=true;loadNewFVForCachedHTML();return;}
convertToClientFormatAllFields();document.getElementById("FIELDVALUES").ondatasetcomplete="";gCurrentFieldValuesDataIsland=vRoot;gCurrentFieldValues=vRoot.getElementsByTagName("ROW")[0];copyLastFieldValues();if(gRefreshCachedHTMLData)
{gRefreshCachedHTMLData=false;checkAllCustomFieldValues();}
implementAttributesAll();if(getFieldValue("isthiscopyrecord")=="true")
{setTimeout("postCopyRecordProcessing()",500);}}}
function checkIsItemHighlightedLB(pListBox)
{var vTable=document.getElementById(pListBox+'_table');for(var i=0;i<vTable.rows.length;i++)
{pRowObject=vTable.rows[i]
vValueObj=vTable.rows[i].cells[0].children[0]
if(vTable.clickOption=='highlight')
{if(pRowObject.className=="LBHighlightItemSelected")
{return true;}}}
return false;}
function removeEmptyNodeLB(pElement)
{var vElement;if(typeof(pElement)=="object")
{vElement=pElement.id;}
else
{vElement=pElement;}
var vRoot=document.getElementById("FIELDVALUES").XMLDocument;var vListBoxNode=vRoot.getElementsByTagName(vElement)[0];var vListBoxOptionNode=vListBoxNode.getElementsByTagName("option")[0];if(isObject(vListBoxOptionNode))
{var vValueNode=vListBoxOptionNode.getElementsByTagName("value")[0];if(vValueNode.childNodes.length==0)
{vListBoxNode.removeChild(vListBoxOptionNode);}}}
function valueSelectedLB(pRowObject)
{var VTable;var vParentDiv;var vSelected;var bShiftKey=false;var bCtrlKey=false;if(isObject(window.event))
{bShiftKey=event.shiftKey;bCtrlKey=event.ctrlKey;}
vTable=pRowObject
while(vTable.tagName!=='TABLE')
{vTable=vTable.parentElement}
vParentDiv=vTable
while(vParentDiv.tagName!="DIV")
{vParentDiv=vParentDiv.parentElement;}
var vValueObj=vTable.rows[pRowObject.rowIndex].cells[0].children[0]
var vSelectedObj=vTable.rows[pRowObject.rowIndex].cells[1].children[0]
if(vTable.rows[pRowObject.rowIndex].cells.length==4)
{var vImageObj=vTable.rows[pRowObject.rowIndex].cells[2].children[0]
var vDisplayObj=vTable.rows[pRowObject.rowIndex].cells[3].children[0]}
else
{var vDisplayObj=vTable.rows[pRowObject.rowIndex].cells[2].children[0]}
var vLastChangedRow=vParentDiv.lastChangeRow;if(vTable.clickOption=='select')
{vSelected=vSelectedObj.innerText;if(vSelected.toLowerCase()=="true")
{vSelectedObj.innerText="false";pRowObject.className="LBSelectItem"
vParentDiv.lastChangeRow=pRowObject.rowIndex;vParentDiv.lastChangeSelected="false"
vParentDiv.lastChangeValue=vValueObj.innerText;vParentDiv.lastChangeDisplay=vDisplayObj.innerText;}
else
{var bHasSelected=false;if(bShiftKey)
{var vTemp=getSelectedValuesLB(vParentDiv.id);bHasSelected=(vTemp[0].length>0);}
if(vTable.multiSelect=='false'||!bCtrlKey)
{for(var i=0;i<vTable.rows.length;i++)
{vTable.rows[i].cells[1].children[0].innerText="false";vTable.rows[i].className="LBSelectItem";}}
if(vTable.multiSelect=='true'&&bShiftKey)
{if(isObject(vLastChangedRow)&&bHasSelected)
{var rmin=vLastChangedRow>pRowObject.rowIndex?pRowObject.rowIndex:vLastChangedRow;var rmax=vLastChangedRow>pRowObject.rowIndex?vLastChangedRow:pRowObject.rowIndex;for(var i=rmin;i<rmax+1;i++)
{vTable.rows[i].cells[1].children[0].innerText="true";vTable.rows[i].className="LBSelectItemSelected";}}}
vSelectedObj.innerText="true";pRowObject.className="LBSelectItemSelected";vParentDiv.lastChangeRow=pRowObject.rowIndex;vParentDiv.lastChangeSelected=vSelectedObj.innerText
vParentDiv.lastChangeValue=vValueObj.innerText;vParentDiv.lastChangeDisplay=vDisplayObj.innerText;}}
if(vTable.clickOption=='highlight')
{if(pRowObject.className=="LBHighlightItemSelected")
{pRowObject.className="LBHighlightItem"}
else
{if(vTable.multiSelect=='false'||!bCtrlKey)
{for(var i=0;i<vTable.rows.length;i++)
{vTable.rows[i].className="LBHighlightItem";}}
if(vTable.multiSelect=='true'&&bShiftKey)
{if(isObject(vLastChangedRow))
{var rmin=vLastChangedRow>pRowObject.rowIndex?pRowObject.rowIndex:vLastChangedRow;var rmax=vLastChangedRow>pRowObject.rowIndex?vLastChangedRow:pRowObject.rowIndex;for(var i=rmin;i<rmax+1;i++)
{vTable.rows[i].className="LBHighlightItemSelected";}}}
pRowObject.className="LBHighlightItemSelected";}
vParentDiv.lastChangeRow=pRowObject.rowIndex;vParentDiv.lastChangeSelected=vSelectedObj.innerText
vParentDiv.lastChangeValue=vValueObj.innerText;vParentDiv.lastChangeDisplay=vDisplayObj.innerText;}
if(vParentDiv.custom_onchange)
{eval(vParentDiv.custom_onchange)}}
function MoveItemsLB(pFromListBox,pToListBox)
{clearDisplayFieldMessage(pFromListBox);clearDisplayFieldMessage(pToListBox);var vSelectedItems=getSelectedValuesLB(pFromListBox);var vSelectedItemsRecoredIndex=vSelectedItems[3];var vRoot=document.getElementById("FIELDVALUES").XMLDocument;var vSourceNode=vRoot.getElementsByTagName(pFromListBox)[0];var vDestinationNode=vRoot.getElementsByTagName(pToListBox)[0];for(var i=0;i<vSelectedItemsRecoredIndex.length;i++)
{var vNodeToMove=vSourceNode.childNodes(vSelectedItemsRecoredIndex[i]);if(vNodeToMove.childNodes(0).hasChildNodes&&vNodeToMove.childNodes(0).childNodes(0).nodeType==3)
{var vClone=vNodeToMove.cloneNode(true);vDestinationNode.appendChild(vClone);}}
for(var x=vSelectedItemsRecoredIndex.length-1;x>-1;x--)
{var vNodeToRemove=vSourceNode.childNodes(vSelectedItemsRecoredIndex[x]);if(vNodeToRemove.childNodes(0).hasChildNodes&&vNodeToMove.childNodes(0).childNodes(0).nodeType==3)
{vSourceNode.removeChild(vNodeToRemove);}}}
function MoveAllItemsLB(pFromListBox,pToListBox)
{var vRoot=document.getElementById("FIELDVALUES").XMLDocument;var vSourceNode=vRoot.getElementsByTagName(pFromListBox)[0];var vDestinationNode=vRoot.getElementsByTagName(pToListBox)[0];for(var i=0;i<vSourceNode.childNodes.length;i++)
{var vNodeToMove=vSourceNode.childNodes(i);if(vNodeToMove.childNodes(0).hasChildNodes&&vNodeToMove.childNodes(0).childNodes(0).nodeType==3)
{var vClone=vNodeToMove.cloneNode(true);vDestinationNode.appendChild(vClone);}}
for(var x=vSourceNode.childNodes.length-1;x>-1;x--)
{var vNodeToRemove=vSourceNode.childNodes(x);if(vNodeToRemove.childNodes(0).hasChildNodes&&vNodeToMove.childNodes(0).childNodes(0).nodeType==3)
{vSourceNode.removeChild(vNodeToRemove);}}}
function getSelectedValuesLB(pDivName)
{var vSelectedInfo=new Array();var vRowInfo=new Array();var vValueInfo=new Array();var vDisplayInfo=new Array();var vRecordIndex=new Array();var vTable;var vSelected;var vValueObj;var vSelectedObj;var vImageObj;var vDisplayObj;var vSelectedCounter=-1;vTable=document.getElementById(pDivName+'_table')
for(var i=0;i<vTable.rows.length;i++)
{vValueObj=vTable.rows[i].cells[0].children[0]
vSelectedObj=vTable.rows[i].cells[1].children[0]
if(vTable.rows[i].cells.length==4)
{vImageObj=vTable.rows[i].cells[2].children[0]
vDisplayObj=vTable.rows[i].cells[3].children[0]}
else
{vDisplayObj=vTable.rows[i].cells[2].children[0]}
vSelected=vSelectedObj.innerText;if(vSelected.toLowerCase()=="true")
{vSelectedCounter++;vRowInfo[vSelectedCounter]=vSelectedObj.innerText
vValueInfo[vSelectedCounter]=vValueObj.innerText
vDisplayInfo[vSelectedCounter]=vDisplayObj.innerText
vRecordIndex[vSelectedCounter]=vValueObj.recordNumber-1;}}
vSelectedInfo[0]=vRowInfo;vSelectedInfo[1]=vValueInfo;vSelectedInfo[2]=vDisplayInfo;vSelectedInfo[3]=vRecordIndex
return vSelectedInfo;}
function markSelectedValuesLB(pListBox)
{var vTable;if(typeof(pListBox)=="string")
{vTable=document.getElementById(pListBox+'_table');}
else
{vTable=document.getElementById(pListBox.id+'_table');}
var vSelected;for(var i=0;i<vTable.rows.length;i++)
{vSelected=vTable.rows[i].cells[1].children[0].innerText;if(vSelected.toLowerCase()=="true")
{valueSelectedLB(vTable.rows[i])}}}
var gLastUnselectedValueDDLB="";var gLastSelectedValueDDLB="";var gCurrentTextFieldDDLB;var gCurrentCodeFieldDDLB;var gTableObjectDDLB;function getNextPriorValuesDDLB(pNextOrPrior)
{if(document.readyState!="complete")
{return false;}
var vNewRow=0;if(gTableObjectDDLB.rows.length==0)
{return;}
if(typeof(gTableObjectDDLB.selectedRow)=='undefined'||gTableObjectDDLB.selectedRow==-1)
{vNewRow=0;}
else
{if(pNextOrPrior=='next')
{vNewRow=gTableObjectDDLB.selectedRow+1
if(vNewRow>gTableObjectDDLB.rows.length-1)
{vNewRow=0}}
else
{vNewRow=gTableObjectDDLB.selectedRow-1
if(vNewRow<0)
{vNewRow=gTableObjectDDLB.rows.length-1}}}
resetSelectedDDLB()
gTableObjectDDLB.selectedRow=vNewRow;gTableObjectDDLB.rows[vNewRow].className="formselect"
vRowValue=gTableObjectDDLB.rows[vNewRow].cells[0].children[0].innerText
vRowDisplay=gTableObjectDDLB.rows[vNewRow].cells[1].children[0].innerText
gCurrentCodeFieldDDLB.value=vRowValue;gCurrentTextFieldDDLB.value=vRowDisplay;var vTR=gTableObjectDDLB.rows[vNewRow]
var vDiv=document.getElementById(gCurrentCodeFieldDDLB.name+'_options')
vDiv.scrollTop=vTR.offsetTop;}
function initializeVariablesDDLB(pTextField)
{if(document.readyState!="complete")
{return false;}
gCurrentTextFieldDDLB=pTextField;gCurrentCodeFieldDDLB=document.getElementById(pTextField.codefieldid)
gTableObjectDDLB=document.getElementById(pTextField.ddlbid+'_table')
gLastUnselectedValueDDLB="";gLastSelectedValueDDLB=pTextField.value}
function arrowClickedDDLB(pArrowButton)
{if(document.readyState!="complete"||!databindingFinished(pArrowButton.id))
{return false;}
initializeVariablesDDLB(document.getElementById(pArrowButton.fieldid));openOrCloseDDLB(pArrowButton,'');setSelectedRowDDLB(gCurrentTextFieldDDLB,gCurrentTextFieldDDLB.value);gCurrentTextFieldDDLB.focus()}
function databindingFinished(pArrowId)
{var optid=pArrowId.replace(/_arrow/g,"_options");var optable=document.getElementById(optid+"_table");var xmlnode=document.getElementById("FIELDVALUES").XMLDocument.selectSingleNode("/FIELDVALUES/ROW/"+optid);if(!isObject(optable)||!isObject(xmlnode))
return true;return optable.rows.length==xmlnode.childNodes.length;}
function onactivateDDLB(pTextField)
{if(document.readyState!="complete")
{return false;}
initializeVariablesDDLB(pTextField)
setSelectedRowDDLB(pTextField,pTextField.value)
pTextField.select()
if(typeof pTextField.lastSelectedValue=="undefined")
{pTextField.lastSelectedValue=pTextField.value}}
function ondeactivateDDLB(pTextField)
{if(document.readyState!="complete")
{return false;}
var vClickedElement=document.activeElement
if(!vClickedElement)
vClickedElement=event.srcElement;if(vClickedElement)
{while(vClickedElement.tagName=='TD'||vClickedElement.tagName=='TR'||vClickedElement.tagName=='TBODY')
{vClickedElement=vClickedElement.parentElement}}
else
{return;}
if(vClickedElement==gTableObjectDDLB||vClickedElement==document.getElementById(gCurrentCodeFieldDDLB.name+'_arrow')||vClickedElement==document.getElementById(gCurrentCodeFieldDDLB.name+'_options'))
{return;}
openOrCloseDDLB(pTextField,'close')
if(pTextField.lastSelectedValue!==pTextField.value)
{var elem=null;if(isObject(FIELDVALUES))
elem=FIELDVALUES.selectSingleNode("FIELDVALUES/ROW/"+pTextField.ddlbid+"/option[display='"+replaceStrs(pTextField.value)+"']");if(elem!=null||!isObject(FIELDVALUES))
{pTextField.lastSelectedValue=pTextField.value;pTextField.fireEvent("onchange")
if(pTextField.custom_onblur)
{eval(pTextField.custom_onblur)}}
else if(isblank(pTextField.value))
{pTextField.lastSelectedValue=pTextField.value;if(pTextField.run_custom_onblur_on_blank)
{if(pTextField.custom_onblur)
{eval(pTextField.custom_onblur)}}}}}
function valueSelectedDDLB(pRowObject)
{if(document.readyState!="complete")
{return false;}
var vRowValue;var vRowDisplay;resetSelectedDDLB()
gTableObjectDDLB.rows[pRowObject.rowIndex].className="formselect"
gTableObjectDDLB.selectedRow=pRowObject.rowIndex
vRowValue=gTableObjectDDLB.rows[pRowObject.rowIndex].cells[0].children[0].innerText
vRowDisplay=gTableObjectDDLB.rows[pRowObject.rowIndex].cells[1].children[0].innerText
gCurrentCodeFieldDDLB.value=vRowValue;gCurrentTextFieldDDLB.value=vRowDisplay;openOrCloseDDLB(gCurrentTextFieldDDLB,'close')
gCurrentTextFieldDDLB.focus();if(gCurrentTextFieldDDLB.lastSelectedValue!==gCurrentTextFieldDDLB.value)
{gCurrentTextFieldDDLB.lastSelectedValue=gCurrentTextFieldDDLB.value;if(gCurrentTextFieldDDLB.custom_onblur)
{eval(gCurrentTextFieldDDLB.custom_onblur)}}}
function OpenDLLBOnDataLoad()
{if(window.event.srcElement.readyState.toLowerCase()=="complete")
{openOrCloseDDLB(new Object(),"open");}}
function openOrCloseDDLB(pObject,pRequiredAction)
{if(document.readyState!="complete")
{return false;}
var vDiv;var vMaxWidth;var vTextPos;var vTextRange
var vCurrentValue
var vOptionDisplay;var vOptionTitle;var vEllipsesWidth;vDiv=document.getElementById(gCurrentCodeFieldDDLB.name+'_options')
var vOptionsTable=document.getElementById(vDiv.id+"_table")
if(vOptionsTable==null)
return false;switch(pRequiredAction)
{case'open':vDiv.style.display="block";vOptionsTable.attachEvent("onreadystatechange",OpenDLLBOnDataLoad)
break;case'close':vDiv.style.display="none";vOptionsTable.detachEvent("onreadystatechange",OpenDLLBOnDataLoad)
return;default:if(vDiv.style.display=="none")
{vDiv.style.display="block";vOptionsTable.attachEvent("onreadystatechange",OpenDLLBOnDataLoad);}
else
{vDiv.style.display="none";vOptionsTable.detachEvent("onreadystatechange",OpenDLLBOnDataLoad)
return;}}
gTableObjectDDLB=document.getElementById(gCurrentTextFieldDDLB.ddlbid+'_table');if(gTableObjectDDLB.rows.length>10)
{vDiv.style.height=137
vDiv.style.overflowY="auto"}
else
{vDiv.style.height=(gTableObjectDDLB.rows.length*13)+2}
vMaxWidth=parseInt(gCurrentTextFieldDDLB.style.width,10)+16;if(isNaN(vMaxWidth))
vMaxWidth=parseInt(gCurrentTextFieldDDLB.offsetWidth,10)+16;vDiv.style.width=vMaxWidth;setElementPosition(vDiv.id,gCurrentTextFieldDDLB.id);if(gTableObjectDDLB.rows.length==0)
{return;}
vOptionDisplay=gTableObjectDDLB.rows[0].cells[2].children[0].innerText;if(vOptionDisplay!=""&&vOptionDisplay!=null)
{return;}
if(gTableObjectDDLB.rows.length>1)
{vOptionDisplay=gTableObjectDDLB.rows[1].cells[2].children[0].innerText;if(vOptionDisplay!=""&&vOptionDisplay!=null)
{return;}}
vCurrentValue=gCurrentTextFieldDDLB.value;gCurrentTextFieldDDLB.value=" ...";vTextRange=gCurrentTextFieldDDLB.createTextRange()
vEllipsesWidth=vTextRange.boundingWidth
vMaxWidth=vDiv.clientWidth;if(gTableObjectDDLB.rows.length>10)
{vMaxWidth=vMaxWidth-18;}
for(i=0;i<gTableObjectDDLB.rows.length;i++)
{gCurrentTextFieldDDLB.value=gTableObjectDDLB.rows[i].cells[1].children[0].innerText;vTextRange=gCurrentTextFieldDDLB.createTextRange()
if(vTextRange.boundingWidth>vMaxWidth)
{vTextPos=vMaxWidth/10;vTextRange.moveEnd("character",vTextPos+(-1*vTextRange.text.length))
while(vTextRange.boundingWidth<vMaxWidth-vEllipsesWidth)
{vTextRange.moveEnd("character",1)}
vTextRange.moveEnd("character",-1)
vOptionDisplay=vTextRange.text+" ...";vOptionTitle=gCurrentTextFieldDDLB.value;}
else
{vOptionDisplay=vTextRange.text
vOptionTitle=null;}
gTableObjectDDLB.rows[i].cells[2].children[0].innerText=vOptionDisplay;if(vOptionTitle!=null)
{gTableObjectDDLB.rows[i].cells[2].children[0].title=vOptionTitle;}}
gCurrentTextFieldDDLB.value=vCurrentValue;if(pRequiredAction=="open"||pRequiredAction==""||pRequiredAction==null)
{setElementPosition(vDiv.id,gCurrentTextFieldDDLB.id);gCurrentTextFieldDDLB.select();}}
function allowsEntrySetSpecialValueDDLB()
{gLastUnselectedValueDDLB=gCurrentTextFieldDDLB.value;gLastSelectedValueDDLB="";resetSelectedDDLB();gCurrentCodeFieldDDLB.value=gCurrentTextFieldDDLB.value;return;}
function findValueDDLB()
{if(document.readyState!="complete")
{return false;}
var vNewDisplay;var vNewValue;var vSearchString=gCurrentTextFieldDDLB.value
vNewValue=setSelectedRowDDLB(gCurrentTextFieldDDLB,vSearchString)
if(vNewValue!==""&&vNewValue==false)
{if(gCurrentTextFieldDDLB.ddlbtype=="allowsentry")
{gLastUnselectedValueDDLB=vSearchString;gLastSelectedValueDDLB="";resetSelectedDDLB();gCurrentCodeFieldDDLB.value=gCurrentTextFieldDDLB.value;return;}
else
{gCurrentTextFieldDDLB.value=gLastUnselectedValueDDLB+gLastSelectedValueDDLB;var r=gCurrentTextFieldDDLB.createTextRange();r.moveStart('character',gLastUnselectedValueDDLB.length);r.select();soundError()
setSelectedRowDDLB(gCurrentTextFieldDDLB,gCurrentTextFieldDDLB.value)
return;}}
vNewDisplay=vNewValue.substring(vNewValue.indexOf("||")+2)
vNewValue=vNewValue.substring(0,vNewValue.indexOf("||"))
gLastUnselectedValueDDLB=vNewDisplay.substring(0,gCurrentTextFieldDDLB.value.length)
gLastSelectedValueDDLB=vNewDisplay.substring(gCurrentTextFieldDDLB.value.length)
gCurrentTextFieldDDLB.value=vNewDisplay;gCurrentCodeFieldDDLB.value=vNewValue;var r=gCurrentTextFieldDDLB.createTextRange();r.moveStart('character',vSearchString.length);r.select();}
function handleOnclickDDLB(pTextField)
{if(document.readyState!="complete")
{return false;}
initializeVariablesDDLB(pTextField);openOrCloseDDLB(pTextField,'');setSelectedRowDDLB(pTextField,pTextField.value)
pTextField.select()}
function handleOnkeydownDDLB(pObject)
{gDDLBKeyDownProcessed=true;if(document.readyState!="complete")
{return false;}
var vSearchString;var vChar;var vNewValue
if((event.ctrlKey&&window.event.keyCode!=86)||event.altKey||window.event.keyCode==9||window.event.keyCode==16||(event.keyCode>111&&event.keyCode<124))
{if(window.event.keyCode==9)
{openOrCloseDDLB(pObject,'close');}
return;}
openOrCloseDDLB(pObject,'open');switch(window.event.keyCode)
{case 36:handleOnclickDDLB(pObject)
window.event.returnValue=false;return;case 13:handleOnclickDDLB(pObject);var vTR=gTableObjectDDLB.rows[gTableObjectDDLB.selectedRow]
if(isObject(vTR))
valueSelectedDDLB(vTR);window.event.returnValue=false;var shouldRunQuickQuery=(window.event.srcElement.id=="selfiltervaluectrl_display");if(shouldRunQuickQuery){runQuickQuery();}
return;case 40:getNextPriorValuesDDLB('next')
return;case 38:getNextPriorValuesDDLB('prior')
return;case 37:case 8:if(pObject.ddlbtype=="allowsentry")
{window.event.returnValue=true;setTimeout("allowsEntrySetSpecialValueDDLB()",100)
return;}
else
{pObject.value=gLastUnselectedValueDDLB.substring(0,gLastUnselectedValueDDLB.length-1)
window.event.returnValue=false;break;}
case 46:if(pObject.ddlbtype=="allowsentry")
{window.event.returnValue=true;setTimeout("allowsEntrySetSpecialValueDDLB()",100);return;}
case 39:if(pObject.ddlbtype=="allowsentry")
{window.event.returnValue=true;setTimeout("allowsEntrySetSpecialValueDDLB()",100)
return;}}
setTimeout("findValueDDLB()",100)
return;}
function resetSelectedDDLB()
{if(document.readyState!="complete")
{return false;}
if(typeof(gTableObjectDDLB.selectedRow)!=='undefined')
{if(gTableObjectDDLB.selectedRow!==-1)
{if(gTableObjectDDLB.rows.length<=gTableObjectDDLB.selectedRow)
{gTableObjectDDLB.selectedRow=-1
return;}
gTableObjectDDLB.rows[gTableObjectDDLB.selectedRow].className="form"
gTableObjectDDLB.selectedRow=-1}}}
function setSelectedRowDDLB(pObject,pStringValue)
{if(document.readyState!="complete"||gTableObjectDDLB==null)
{return false;}
var i;var vOptionValue;var vReturnValue=pStringValue;var vReturnDisplay
var vFirstFound=false;var vTotalOptions=gTableObjectDDLB.rows.length;resetSelectedDDLB()
if(pStringValue=="")
{return"||";}
for(i=0;i<vTotalOptions;i++)
{vOptionValue=gTableObjectDDLB.rows[i].cells[0].children[0].innerText
vOptionDisplay=gTableObjectDDLB.rows[i].cells[1].children[0].innerText
if(pStringValue.toUpperCase()==vOptionDisplay.substring(0,pStringValue.length).toUpperCase())
{if(!vFirstFound)
{vFirstFound=true;gTableObjectDDLB.selectedRow=i;gTableObjectDDLB.rows[i].className="formselect"
var vTR=gTableObjectDDLB.rows[i]
var vDiv=document.getElementById(gCurrentCodeFieldDDLB.name+'_options')
vDiv.scrollTop=vTR.offsetTop;break;}}}
if(vFirstFound)
{return vOptionValue+'||'+vOptionDisplay;}
else
{return false}}
function rowhoverInDDLB(pTableRow)
{if(document.readyState!="complete")
{return false;}
resetSelectedDDLB();pTableRow.className="formselect";}
function rowhoverOutDDLB(pTableRow)
{if(document.readyState!="complete")
{return false;}
pTableRow.className="form";}
function rowhoverInTableDDLB()
{if(document.readyState!="complete")
{return false;}
var vTR=getTRElementDDLB()
if(vTR)
{rowhoverInDDLB(vTR);}}
function rowhoverOutTableDDLB()
{if(document.readyState!="complete")
{return false;}
var vTR=getTRElementDDLB()
if(vTR)
{rowhoverOutDDLB(vTR);}}
function setDisplayFromValueDDLB(pTextField,pStringValue)
{if(document.readyState!="complete")
{return false;}
var i;var vOptionValue;initializeVariablesDDLB(document.getElementById(pTextField))
var vTotalOptions=gTableObjectDDLB.rows.length;for(i=0;i<vTotalOptions;i++)
{vOptionValue=gTableObjectDDLB.rows[i].cells[0].children[0].innerText
vOptionDisplay=gTableObjectDDLB.rows[i].cells[1].children[0].innerText
if(pStringValue==vOptionValue)
{gCurrentTextFieldDDLB.value=vOptionDisplay;gCurrentCodeFieldDDLB.value=vOptionValue;return true;}}
if(pStringValue=="")
{gCurrentTextFieldDDLB.value="";gCurrentCodeFieldDDLB.value="";return true}
return false}
function getCurrentDisplayValueDDLB(pTextField)
{if(document.readyState!="complete")
{return false;}
initializeVariablesDDLB(document.getElementById(pTextField))
return gCurrentTextFieldDDLB.value;}
function getCurrentCodeValueDDLB(pTextField)
{if(document.readyState!="complete")
{return false;}
initializeVariablesDDLB(document.getElementById(pTextField))
return gCurrentCodeFieldDDLB.value;}
function deleteAllItemsFromDDLB(pTextField)
{if(document.readyState!="complete")
{return false;}
if(pTextField.indexOf('_display')==-1)
{pTextField=pTextField+'_display';}
var vCurrentTextFieldDDLB=document.getElementById(pTextField);var vCurrentCodeFieldDDLB=document.getElementById(vCurrentTextFieldDDLB.codefieldid)
var vTableObjectDDLB=document.getElementById(vCurrentTextFieldDDLB.ddlbid+'_table')
var vTBODY=document.createElement("TBODY")
vPositionElement=vTableObjectDDLB.getElementsByTagName("TBODY")[0];vPositionElement.replaceNode(vTBODY);}
function addSingleItemToDDLB(pTextField,pIndex,pValue,pDisplay)
{if(document.readyState!="complete")
{return false;}
if(pTextField.indexOf('_display')==-1)
{pTextField=pTextField+'_display';}
var vCurrentTextFieldDDLB=document.getElementById(pTextField);var vCurrentCodeFieldDDLB=document.getElementById(vCurrentTextFieldDDLB.codefieldid)
var vTableObjectDDLB=document.getElementById(vCurrentTextFieldDDLB.ddlbid+'_table')
var vPositionElement;var vTableRows=vTableObjectDDLB.rows.length;if(pIndex<0||pIndex>=vTableRows)
{pIndex=vTableRows;}
var vTR=document.createElement("TR")
vTR.className="form"
var vTD1=document.createElement("TD")
var vSpan1=document.createElement("SPAN")
vSpan1.appendChild(document.createTextNode(pValue))
vSpan1.style.display="none";vTD1.appendChild(vSpan1)
var vTD2=document.createElement("TD")
var vSpan2=document.createElement("SPAN")
vSpan2.appendChild(document.createTextNode(pDisplay))
vSpan2.style.display="none";vTD2.appendChild(vSpan2)
var vTD3=document.createElement("TD")
var vSpan3=document.createElement("SPAN")
vTD3.appendChild(vSpan3)
vTR.appendChild(vTD1);vTR.appendChild(vTD2);vTR.appendChild(vTD3);var vNewRowObject;if(vTableRows==0)
{vPositionElement=vTableObjectDDLB.getElementsByTagName("TBODY")[0];vNewRowObject=vPositionElement.appendChild(vTR);}
else
{if(pIndex==0)
{vPositionElement=vTableObjectDDLB.getElementsByTagName("TR")[0];vNewRowObject=vPositionElement.insertAdjacentElement('beforeBegin',vTR);}
else
{vPositionElement=vTableObjectDDLB.getElementsByTagName("TR")[pIndex-1];if(pIndex==vTableRows)
{vNewRowObject=vPositionElement.insertAdjacentElement('afterEnd',vTR);}
else
{vNewRowObject=vPositionElement.insertAdjacentElement('beforeBegin',vTR);}}}}
function valueSelectedTableDDLB()
{if(document.readyState!="complete")
{return false;}
var vTR;var i=0;switch(event.srcElement.tagName)
{case'TR':vTR=event.srcElement;break;case'TD':case'SPAN':vTR=event.srcElement.parentElement;while(vTR.tagName=='TD'||vTR.tagName=='SPAN')
{i++
if(i>10)
{return;}
vTR=vTR.parentElement;}
break;default:return;}
valueSelectedDDLB(vTR);}
function getTRElementDDLB(pObject)
{if(document.readyState!="complete")
{return false;}
var vTR;var i=0;switch(event.srcElement.tagName)
{case'TR':vTR=event.srcElement;break;case'TD':case'SPAN':vTR=event.srcElement.parentElement;while(vTR.tagName=='TD'||vTR.tagName=='SPAN')
{i++
if(i>10)
{return false;}
vTR=vTR.parentElement;}
break;default:return false;}
return vTR;}
function icondown(obj,pImgUrl)
{obj.src=pImgUrl;}
function setglasstop(pErrorMessage)
{if(pErrorMessage!="undefined"&&!isblank(pErrorMessage))
{document.getElementById("glasstop").errorMessage=pErrorMessage;}
document.getElementById("glasstop").style.display="block";window.glassTopOn=true;getBrowserWindow().gLastGlasstopOn=new Date();}
function removeglasstop(pClearMessage)
{if(pClearMessage=="undefined"||pClearMessage==null)
pClearMessage=false;document.getElementById("glasstop").style.display="none";if(pClearMessage)
{document.getElementById("glasstop").errorMessage=null;}
window.glassTopOn=false;}
function showGlassTopErrorMessage()
{var vErrorMessage=parent.document.getElementById("glasstop").errorMessage;if(vErrorMessage!="undefined"&&!isblank(vErrorMessage))
{parent.displayStandardMessageOnPage(0,"error",vErrorMessage);}}
function initMessages()
{if(!gMessageInitProcessing)
{gMessageInitProcessing=true;implementMessages();}
gMessageInitProcessing=false;}
function messageBarReset()
{var vErrorInMessages=false;if(gErrorInMessages)
vErrorInMessages=true;if(isObject(getBrowserWindow().gMessageBarAutoHideTimer))
getBrowserWindow().clearTimeout(getBrowserWindow().gMessageBarAutoHideTimer)
gErrorInMessages=false;gQuestionInMessages=false;clearMessageBar();var vErrorMessage=document.getElementById("glasstop").errorMessage;if(vErrorMessage=="undefined"||isblank(vErrorMessage))
{removeglasstop();}
implementMessages();messageBarFocusSet(vErrorInMessages);}
function messageBarFocusSet(pErrorInMessages,pOverRide)
{if(!isObject(pOverRide))
{pOverRide=false;}
if(!isObject(pErrorInMessages))
{if(gErrorInMessages)
{pErrorInMessages=true;}
else
{pErrorInMessages=false;}}
try
{if(isObject(window.gMessageInitScope))
{if(gMessageInitScope=='window'||gMessageInitScope=='popupwindow')
{if(pErrorInMessages)
gFocusField=gLastFocusField;setFieldFocus(gFocusField);}
else if(gMessageInitScope=='MAINIFRAME'||gMessageInitScope=='SUBIFRAME')
{var win=getNamedIframeWindow(gMessageInitScope);if(!win)
win=getNamedIframeWindow('MAINIFRAME');if(isObject(win.getFieldValue("pagemode")))
{if((win.getFieldValue("pagemode")).toLowerCase()!="editlayout")
{if(pOverRide)
{if(isObject(window.event))
{killLastKeyPressed();win.setTimeout("setFieldFocus(gFirstFocusField)",250);window.event.cancelBubble=true;return false;}}
else
{if(isObject(win.gFocusField)||isObject(win.gLastFocusField))
{if(pErrorInMessages)
{win.gFocusField=win.gLastFocusField;}
win.setFieldFocus(win.gFocusField);}
else
{win.setFieldFocus(win.gFirstFocusField);}}}}}}}
catch(e)
{}}
function implementMessages()
{var vXmlDoc=document.getElementById("MESSAGE").XMLDocument;var vMessageContainerNode=vXmlDoc.documentElement;var vHasPageMessages=false;var vHasFieldMessages=false;if(vMessageContainerNode!=null)
{for(var i=0;i<vMessageContainerNode.childNodes.length;i++)
{var vCurrentMessageAttributesNode=vMessageContainerNode.childNodes.item(i).attributes;var vCurrentMessageScope=vCurrentMessageAttributesNode.getNamedItem("scope").value;if(vCurrentMessageScope=="page")
{vHasPageMessages=true;}
if(vCurrentMessageScope=="field")
{vHasFieldMessages=true;}}}
if(vHasFieldMessages)
{implementFieldMessages();}
if(vHasPageMessages)
{if(isObject(window.frameElement)&&(!isObject(window.frameElement.localMessages)||window.frameElement.localMessages!="true")&&!(isObject(window.gMessageInitScope)&&gMessageInitScope=="popupwindow"))
{transferMessagesToParent();}
else
{if(!isMessageBarOpen())
{implementPageMessages();}
else
{if(checkForQueuedQuestions())
{setglasstop();}
else
changeMessageButtonToMore();}}}}
function implementPageMessages()
{removeMessageBarAutoHideTimeout();var vDisplayOnlyMessageFound=false;var vDisplayOnlyNonSuccessFound=false;var vQueuedQuestionMessagesFound=false;var vMessagesStillInQueue=false;var vXmlDoc=document.getElementById("MESSAGE").XMLDocument;var vMessageContainerNode=vXmlDoc.documentElement;var vNumOfDisplayOnlyMessagesProcessed=0;if(vMessageContainerNode!=null)
{var vDisplayMessageIndexArray=new Array();for(var i=0;i<vMessageContainerNode.childNodes.length;i++)
{if(vNumOfDisplayOnlyMessagesProcessed>7)
{vMessagesStillInQueue=true;setglasstop();changeMessageButtonToMore();break;}
var vCurrentMessageAttributesNode=vMessageContainerNode.childNodes.item(i).attributes;var vCurrentMessageScope=vCurrentMessageAttributesNode.getNamedItem("scope").value;if(vCurrentMessageScope=="page")
{var vCurrentMessageType=vCurrentMessageAttributesNode.getNamedItem("type").value;if(vCurrentMessageType!="question")
{if(vCurrentMessageType.toLowerCase()!="confirm")
{vDisplayOnlyNonSuccessFound=true;}
var vCurrentMessageText=vCurrentMessageAttributesNode.getNamedItem("label").value;var vArgs;var vArgArray;if(isObject(vCurrentMessageAttributesNode.getNamedItem("labelargs")))
{vArgs=vCurrentMessageAttributesNode.getNamedItem("labelargs").value;vArgArray=vArgs.split(",")}
else
{vArgArray=null;}
vDisplayOnlyMessageFound=true;renderStandardMessageOnPage(i,vCurrentMessageType,vCurrentMessageText,vArgArray);vNumOfDisplayOnlyMessagesProcessed++;vDisplayMessageIndexArray.push(i);}}}
for(x=vDisplayMessageIndexArray.length-1;x>-1;x--)
{vMessageContainerNode.removeChild(vMessageContainerNode.childNodes.item(vDisplayMessageIndexArray[x]));}
if(vDisplayOnlyMessageFound)
{if(vDisplayOnlyNonSuccessFound)
{setglasstop();}
vQueuedQuestionMessagesFound=checkForQueuedQuestions();if(vQueuedQuestionMessagesFound)
{setglasstop();changeMessageButtonToMore();}
if(!vQueuedQuestionMessagesFound&&!vDisplayOnlyNonSuccessFound&&!gMessageBarAutoHideDisabled)
{if(gSuccessMsgTimeout==0)
{messageBarReset();}
else
{setMessageBarAutoHideTimeout();}}
else
{gMessageBarAutoHideDisabled=true;}
return;}
else
{for(var i=0;i<vMessageContainerNode.childNodes.length;i++)
{var vCurrentMessageAttributesNode=vMessageContainerNode.childNodes.item(i).attributes;var vCurrentMessageScope=vCurrentMessageAttributesNode.getNamedItem("scope").value;if(vCurrentMessageScope=="page")
{var vCurrentMessageType=vCurrentMessageAttributesNode.getNamedItem("type").value;if(vCurrentMessageType=="question")
{var vCurrentMessageText=vCurrentMessageAttributesNode.getNamedItem("label").value;var vCurrentMessageType=vCurrentMessageAttributesNode.getNamedItem("type").value;var vCurrentMessageQuestionType=vCurrentMessageAttributesNode.getNamedItem("questiontype").value
var vArgs;var vArgArray;if(isObject(vCurrentMessageAttributesNode.getNamedItem("labelargs")))
{vArgs=vCurrentMessageAttributesNode.getNamedItem("labelargs").value;vArgArray=vArgs.split(",")}
else
{vArgArray=null;}
var vHandlerScope=vCurrentMessageAttributesNode.getNamedItem("handlerscope").value
if(vHandlerScope=="")
{vHandlerScope=null;}
if(vCurrentMessageQuestionType.toLowerCase()!="custom")
{var vHandler=vCurrentMessageAttributesNode.getNamedItem("handler").value;renderQuestionMessageOnPage(0,vCurrentMessageQuestionType,vHandler,vCurrentMessageText,vArgArray,vHandlerScope)}
else
{var vBtn1=vCurrentMessageAttributesNode.getNamedItem("button1").value;var vBtn2=vCurrentMessageAttributesNode.getNamedItem("button2").value;var vBtn3=vCurrentMessageAttributesNode.getNamedItem("button3").value;renderCustomQuestionMessageOnPage(0,vBtn1,vBtn2,vBtn3,vCurrentMessageText,vArgArray,vHandlerScope)}
vMessageContainerNode.removeChild(vMessageContainerNode.childNodes.item(i));setglasstop();break;}}}}}
else
{return;}}
function iframeMessageRelay()
{var vMessageDataIsland=window.document.getElementById("MESSAGE");var vXmlDoc=vMessageDataIsland.XMLDocument;var parentDoc=window.parent;var xmlString="<PAGEDATA><MESSAGE>";var vMessageContainerNode=vXmlDoc.documentElement;if(vMessageContainerNode!=null)
{for(var i=vMessageContainerNode.childNodes.length-1;i>-1;i--)
{var vCurrentMessageNode=vMessageContainerNode.childNodes.item(i);var vCurrentMessageAttributesNode=vCurrentMessageNode.attributes;var vCurrentMessageScope=vCurrentMessageAttributesNode.getNamedItem("scope").value;if(vCurrentMessageScope=="page")
{xmlString+=vCurrentMessageNode.xml;vMessageContainerNode.removeChild(vCurrentMessageNode);}}}
xmlString+="</MESSAGE></PAGEDATA>";parentDoc.loadProcessEventReturnXML(xmlString);initMessages();}
function displayStandardMessageOnPage(pMessageNum,pType,pMessage,pArgArray)
{var vMessageXml=buildMessageXML(0,pType,null,null,null,null,null,pMessage,pArgArray,null);sendMessageXmlToDataIsland(vMessageXml);}
function clearPageMessages()
{var vCount=0;var vWindow=getBrowserWindow();var vXmlDoc=vWindow.document.getElementById("MESSAGE");if(isObject(vXmlDoc))
{var vMessageContainerNode=vXmlDoc.documentElement;if(vMessageContainerNode!=null)
{for(var i=vMessageContainerNode.childNodes.length-1;i>-1;i--)
{var vCurrentMessageAttributesNode=vMessageContainerNode.childNodes.item(i).attributes;var vCurrentMessageScope=vCurrentMessageAttributesNode.getNamedItem("scope").value;if(vCurrentMessageScope=="page")
{vMessageContainerNode.removeChild(vMessageContainerNode.childNodes.item(i));}}}
clearMessageBar();}}
function implementFieldMessages()
{clearAllDisplayFieldMessages();var vCount=0;var vWindow=window;var vXmlDoc=vWindow.document.getElementById("MESSAGE").XMLDocument;var vMessageContainerNode=vXmlDoc.documentElement;if(vMessageContainerNode!=null)
{for(var i=vMessageContainerNode.childNodes.length-1;i>-1;i--)
{var vCurrentMessageAttributesNode=vMessageContainerNode.childNodes.item(i).attributes;var vCurrentMessageScope=vCurrentMessageAttributesNode.getNamedItem("scope").value;if(vCurrentMessageScope=="field")
{var vCurrentMessageText=vCurrentMessageAttributesNode.getNamedItem("label").value;var vCurrentMessageFieldId=vCurrentMessageAttributesNode.getNamedItem("field").value;displayMessageOnField(vCurrentMessageFieldId,vCurrentMessageText);vMessageContainerNode.removeChild(vMessageContainerNode.childNodes.item(i));}}}
else
{return;}}
function displayMessageOnField(vObj,pMessage,pRequiredFieldError)
{var vOrigField=null;var pID=null;if(typeof(vObj)=="object")
{vOrigField=vObj;pID=vObj.id;}else
{pID=vObj
vOrigField=document.getElementById(pID);}
if(!isObject(pRequiredFieldError))
{pRequiredFieldError=false;}
if(!isCustomField(pID))
{var idx=-1;idx=pID.indexOf("_MI")
if(idx!=-1)
{pID=pID.substr(0,idx);}
idx=pID.indexOf("_HH")
if(idx!=-1)
{pID=pID.substr(0,idx);}
idx=pID.indexOf("_timeseparator");if(idx!=-1)
{pID=pID.substr(0,idx);}}
clearDisplayFieldMessage(pID);var vTargetElement=vOrigField;if(isObject(vTargetElement))
{var vNewElement=document.createElement("span");vNewElement.id="fieldErr_"+pID;vNewElement.requiredFieldError=pRequiredFieldError;vNewElement.className="EXCEPTIONTEXT";vNewElement.appendChild(document.createElement("br"));vNewElement.appendChild(document.createTextNode(unescape(pMessage)));vTargetElement.parentElement.insertAdjacentElement("beforeEnd",vNewElement);if(isObject(vOrigField)&&isObject(vOrigField.dataFld)&&vOrigField.dataFld!="")
{if(vOrigField.getAttribute("fieldtype")==EG_DATE_FIELD)
{vOrigField.lastFieldErrorValue=vOrigField.value;}else
{vOrigField.lastFieldErrorValue=getFieldValue(vOrigField.dataFld);}
vOrigField.attachEvent("onpropertychange",checkFieldErrorOnChange);}}}
function isFieldMessagePresent()
{var vSpanArray=document.getElementsByTagName("span");for(i=vSpanArray.length;i>0;i--)
{if(vSpanArray[i-1].id.substr(0,9)=="fieldErr_")
{return true;}}
return false;}
function clearDisplayFieldMessage(pID)
{if(!isCustomField(pID))
{var idx=-1;idx=pID.indexOf("_MI")
if(idx!=-1)
{pID=pID.substr(0,idx);}
idx=pID.indexOf("_HH")
if(idx!=-1)
{pID=pID.substr(0,idx);}
idx=pID.indexOf("_timeseparator");if(idx!=-1)
{pID=pID.substr(0,idx);}}
if(document.getElementById("fieldErr_"+pID)==null)
{return;}
document.all("fieldErr_"+pID).removeNode(true);document.getElementById(pID).detachEvent("onpropertychange",checkFieldErrorOnChange);}
function clearAllFieldMessages()
{var vCount=0;var vXmlDoc=document.getElementById("MESSAGE").XMLDocument;var vMessageContainerNode=vXmlDoc.documentElement;if(vMessageContainerNode!=null)
{for(var i=vMessageContainerNode.childNodes.length-1;i>-1;i--)
{var vCurrentMessageAttributesNode=vMessageContainerNode.childNodes.item(i).attributes;var vCurrentMessageScope=vCurrentMessageAttributesNode.getNamedItem("scope").value;if(vCurrentMessageScope=="field")
{vMessageContainerNode.removeChild(vMessageContainerNode.childNodes.item(i));}}}
clearAllDisplayFieldMessages();}
function clearAllDisplayFieldMessages()
{var vSpanArray=document.getElementsByTagName("span");for(i=vSpanArray.length;i>0;i--)
{if(vSpanArray[i-1].id.substr(0,9)=="fieldErr_")
{vElemName=vSpanArray[i-1].id.substr(9);document.getElementById(vElemName).detachEvent("onpropertychange",checkFieldErrorOnChange);vSpanArray[i-1].removeNode(true);}}}
function OpenMessageBar()
{var vMessageBar=window.document.getElementById("messagebar");if(vMessageBar.style.display=="none")
{vMessageBar.style.display="block";vMessageBar.style.pixelTop=window.document.body.clientHeight+window.document.body.scrollTop-document.all.messagebar.clientHeight;}
repositionDivOnBottom("messagebar");if(isObject(document.getElementById("message_button_cell"))&&!document.getElementById("message_button_cell").lastButton)
{killLastKeyPressed();document.getElementById("message_button_cell").focus();}}
function messageBarOpen(pType)
{var vMessageBar=document.getElementById("messagebar");if(typeof(vMessageBar)!="undefined"&&vMessageBar!=null)
{if(vMessageBar.style.display=="block")
{switch(pType)
{case"error":if(gErrorInMessages||getBrowserWindow().gErrorInMessages)
return true;else
break;case"confirm":if(gQuestionInMessages||getBrowserWindow().gQuestionInMessages)
return true;else
break;case"warning":if(gWarningInMessages||getBrowserWindow().gWarningInMessages)
return true;else
break;case"information":if(gInfoInMessages||getBrowserWindow().gInfoInMessages)
return true;else
break;case"question":if(gQuestionInMessages||getBrowserWindow().gQuestionInMessages)
return true;else
break;case"all":if(gQuestionInMessages||getBrowserWindow().gQuestionInMessages||gErrorInMessages||getBrowserWindow().gErrorInMessages||gWarningInMessages||getBrowserWindow().gWarningInMessages||gInfoInMessages||getBrowserWindow().gInfoInMessages)
return true;else
break;default:if(gQuestionInMessages||getBrowserWindow().gQuestionInMessages||gErrorInMessages||getBrowserWindow().gErrorInMessages)
return true;}}}
return false;}
function CloseMessageBar()
{var vMessageBar=document.getElementById("messagebar");if(typeof(vMessageBar)!="undefined"&&vMessageBar!=null)
{if(vMessageBar.style.display=="block"||document.getElementById("messagebar").style.display=="")
{vMessageBar.style.display="none";var vSubIframeWindow=getSubIframeWindow();if(vSubIframeWindow)
vSubIframeWindow.gPerform7iFunctionFoundQuestion=false;}}}
function clearMessageBar()
{var vMessageContainterCell=document.getElementById("message_container_cell");if(typeof(vMessageContainterCell)!="undefined"&&vMessageContainterCell!=null)
{vMessageContainterCell.innerHTML="";}
gMessageBarAutoHideDisabled=false;CloseMessageBar();}
function repositionbar(){var vCount=0;var vWindow=getBrowserWindow();vWindow.document.all.messagebar.style.pixelTop=vWindow.document.body.clientHeight+vWindow.document.body.scrollTop-vWindow.document.all.messagebar.clientHeight;}
function doAddQuickFilterOperator(args){args.QuickFilterOperator=getQuickFilterOperator();return args;}
function addQuickFilterOperator(args){if(!isObject(args)){return doAddQuickFilterOperator(new Array());}else if(typeof(args)=="object"){return doAddQuickFilterOperator(args);}else if(typeof(args)=="string"){return doAddQuickFilterOperator(new String(args));}}
function dstmShowModalDialog(pURL,pArgs,pStyle,appendQ)
{var args=addQuickFilterOperator(pArgs);lastKeyPressedCode=-1;if(appendQ==null)
appendQ=true;if(gPageName!="BSHOME")
{while(pURL!=null&&pURL.indexOf("dataspylist=")>-1)
pURL=pURL.replace("dataspylist=","dataspylists=");}
pStyle=pStyle+";help:no;center:yes";if(isObject(pArgs))
pArgs.openerwindow=this;var vRet=null;if(appendQ)
vRet=window.showModalDialog(pURL+"?popup=TRUE&"+appendSystemFunction("",true,true),args,pStyle);else
vRet=window.showModalDialog(appendSystemFunction(pURL,true,true)+"&popup=TRUE",args,pStyle);if(typeof(vRet)=="undefined"||vRet==null)
return vRet;else if(typeof(vRet)=='string'&&vRet=='LOGIN')
{window.returnValue="LOGIN"
setSystemLogout();}
else
return vRet;}
var usertab_pattern=/UT[0-9]+/;function isUserTab(ptabname)
{return usertab_pattern.test(ptabname);}
function rewriteUserTabRequest(pDataToSend)
{pDataToSend=pDataToSend+"&usertab=true&SYSTEM_FUNCTION_NAME=USRTAB&CURRENT_TAB_NAME="
+urlEncode(gCurrentTab)+"&REAL_SYSTEM_FUNCTION="+urlEncode(gPageName)
if(pDataToSend.indexOf("USER_FUNCTION_NAME")<0)
{if(isObject(window.gUserFunctionName)&&!isblank(gUserFunctionName))
pDataToSend=pDataToSend+'&USER_FUNCTION_NAME='+urlEncode(gUserFunctionName);else
pDataToSend=pDataToSend+'&USER_FUNCTION_NAME='+urlEncode(gPageName);}
return pDataToSend;}
function appendSystemFunction(pDataToSend,leaveTab,isPopup)
{if(isPopup==null)
isPopup=false;if(leaveTab==null)
leaveTab=false;if(isObject(window.gCurrentTab)&&usertab_pattern.test(gCurrentTab))
return rewriteUserTabRequest(pDataToSend);if(pDataToSend.indexOf('SYSTEM_FUNCTION_NAME')==-1&&typeof(gPageName)!="undefined"&&!isblank(gPageName))
{if(isblank(pDataToSend))
pDataToSend='SYSTEM_FUNCTION_NAME='+urlEncode(gPageName);else
pDataToSend=pDataToSend+'&SYSTEM_FUNCTION_NAME='+urlEncode(gPageName);if(!leaveTab)
{if(typeof(gCurrentTab)!="undefined"&&!isblank(gCurrentTab))
pDataToSend=pDataToSend+'&CURRENT_TAB_NAME='+urlEncode(gCurrentTab);else
{if(isObject(eval("parent.gCurrentTab")))
pDataToSend=pDataToSend+'&CURRENT_TAB_NAME='+urlEncode(parent.gCurrentTab);}}
if(typeof(gUserFunctionName)!="undefined"&&!isblank(gUserFunctionName))
pDataToSend=pDataToSend+'&USER_FUNCTION_NAME='+urlEncode(gUserFunctionName);else
pDataToSend=pDataToSend+'&USER_FUNCTION_NAME='+urlEncode(gPageName);}
if((!isPopup)&&isObject(window.document.getElementById("gridhead_table"))&&isObject(window.document.getElementById("gridhead_table").localQuickSortCod))
{pDataToSend=pDataToSend+"&"+window.document.getElementById("gridhead_table").localQuickSortCod;}
if((!isPopup)&&isObject(eval("window.quickFilterCod"))&&window.quickFilterCod!=null)
{pDataToSend=pDataToSend+"&"+window.quickFilterCod;}
return pDataToSend;}
function repositionDivOnBottom(pDiv)
{if(typeof(pDiv)!="undefined"&&pDiv!=null)
{var vDiv
if(typeof(pDiv)!="object")
{vDiv=document.getElementById(pDiv);}
else
{vDiv=pDiv;}
vDiv.style.pixelTop=document.body.clientHeight+document.body.scrollTop-vDiv.clientHeight;}}
function padPortlet(pIslandRef)
{var vRootXml=pIslandRef;var vRowContainerNode=vRootXml.getElementsByTagName("DATA")[0];for(var z=0;z<vRowContainerNode.childNodes.length;z++)
{var vRowNode=vRowContainerNode.childNodes.item(z);for(var y=0;y<vRowNode.childNodes.length;y++)
{if(vRowNode.childNodes.item(y).text=="")
{vRowNode.childNodes.item(y).text=" ";}}}}
function processMessages(oxml)
{messNode=oxml.getElementsByTagName("MESSAGE")
if(messNode.length<1)
return true
mesXML=document.getElementById("message")
if(mesXML.documentElement!=null)
mesXML.removeChild(mesXML.documentElement)
mesXML.loadXML(messNode[0].xml)
return false}
function isUpdateMode()
{if(isObject(eval("window.gPageMode")))
{if(gPageMode=="view")
{return true;}
else
{return false;}}
else
{return false;}}
function copyLastFieldValues(pDataIslandRef,pDataContainerRef)
{var vLastFieldValuesDataIsland=new ActiveXObject("MSXML.DOMDocument");vLastFieldValuesDataIsland.loadXML(gCurrentFieldValuesDataIsland.xml);var vLastFieldValues=vLastFieldValuesDataIsland.getElementsByTagName("ROW")[0];if(isObject(pDataIslandRef)&&isObject(pDataContainerRef))
{pDataContainerRef=null;pDataIslandRef=null;pDataIslandRef=vLastFieldValuesDataIsland;pDataContainerRef=vLastFieldValues}
else
{if(isObject(pDataIslandRef)||isObject(pDataContainerRef))
{vLastFieldValues=null;vLastFieldValuesDataIsland=null;return false;}
gLastFieldValues=null;gLastFieldValuesDataIsland=null;gLastFieldValuesDataIsland=vLastFieldValuesDataIsland;gLastFieldValues=vLastFieldValues}
return true;}
function fieldValuesHasChanges(pDataContainerRef1,pDataContainerRef2)
{var vPageMode=getFieldValue("pagemode").toLowerCase();if(vPageMode=="editlayout"||vPageMode=="previewmode")
{if(gPageLayoutModified)
{return true;}}
if(!isObject(eval("window.gIgnoreFieldValueChanges")))
{gIgnoreFieldValueChanges=false;}
if(!gIgnoreFieldValueChanges)
{if(isObject(eval("window.gGridMode")))
{if(gGridMode=="single-edit"||gGridMode=="multi-edit")
{if(editGridFieldValuesHasChanges())
return true;}}
var vDataContainerRef1;var vDataContainerRef2;if(isObject(pDataContainerRef1)&&isObject(pDataContainerRef2))
{vDataContainerRef1=pDataContainerRef1;vDataContainerRef2=pDataContainerRef2;}
else
{vDataContainerRef1=gLastFieldValues;vDataContainerRef2=gCurrentFieldValues;}
if(isObject(vDataContainerRef1)&&isObject(vDataContainerRef2))
{if(isObject(eval("window.oHTMLEdit"))&&oHTMLEdit!=null)
{var vOrigTextArea=escape(trim(getFieldValue(oHTMLEdit.idTextArea,vDataContainerRef2)));var vCurrentTextArea=escape(trim(getHTMLEditorContents()));vCurrentTextArea=vCurrentTextArea.replace(/%0D%0A/g,"%0A");if(!isblank(vCurrentTextArea)&&isblank(vOrigTextArea)&&isHTMLEditorEmpty())
{vCurrentTextArea="";}
if(vOrigTextArea!=vCurrentTextArea)
{return true;}}
if(vDataContainerRef1.xml!=vDataContainerRef2.xml)
{return true;}}}
var vGridTable=document.getElementById("gridbody_table");if(isObject(vGridTable)&&(vGridTable.gGridMode=="single-edit"||vGridTable.gGridMode=="multi-edit")&&isObject(gChangedRowsDataIsland))
{var vChangeNodes=gChangedRowsDataIsland.selectNodes("GRIDREQUEST/DATA/ROW");if(vChangeNodes.length>0)
{return true;}}
if(isObject(eval("window.doesScreenHaveChanges"))&&window.doesScreenHaveChanges())
{return true;}
return false;}
function fieldNodeExists(pFieldName,pDataContainerRef)
{var vDataContainerRef;if(isObject(pDataContainerRef))
{vDataContainerRef=pDataContainerRef;}
else
{vDataContainerRef=gCurrentFieldValues;}
if(!isObject(vDataContainerRef))
{return false;}
var vNodeToCheck=vDataContainerRef.getElementsByTagName(pFieldName)[0];if(isObject(vNodeToCheck))
{return true;}
else
{return false;}}
function getFieldValue(pFieldName,pDataContainerRef)
{var vDataContainerRef;if(isObject(pDataContainerRef))
{vDataContainerRef=pDataContainerRef;}
else
{vDataContainerRef=gCurrentFieldValues;}
if(!isObject(vDataContainerRef))
{return"";}
var vNodeToCheck=vDataContainerRef.getElementsByTagName(pFieldName)[0];if(isObject(vNodeToCheck)&&isObject(vNodeToCheck.childNodes.item(0))&&vNodeToCheck.childNodes.item(0).nodeType==3)
{return vNodeToCheck.text;}
else
{return"";}}
function getTimeFieldValue(pField,pDataContainerRef)
{var vField;if(typeof(pField)=="string")
{vField=document.getElementById(pField);}
else
{vField=pField;}
if(!isObject(vField))
{return"";}
var vDataContainerRef;if(isObject(pDataContainerRef))
{vDataContainerRef=pDataContainerRef;}
else
{vDataContainerRef=gCurrentFieldValues;}
if(!isObject(vDataContainerRef))
{return"";}
var vHourItem=vDataContainerRef.getElementsByTagName(vField.id+"_HH")[0];var vMinuteItem=vDataContainerRef.getElementsByTagName(vField.id+"_MI")[0];var vHourValue;var vMinuteValue
if(!isObject(vHourItem)||!isObject(vMinuteItem))
{return"";}
if(isObject(vHourItem.text))
{vHourValue=vHourItem.text*3600;}
else
{vHourValue=0;}
if(isObject(vMinuteItem.text))
{vMinuteValue=vMinuteItem.text*60;}
else
{vMinuteValue=0}
if(vHourItem.text==''&&vMinuteItem.text=='')
{return"";}
else
{return vHourValue+vMinuteValue;}}
function getFieldLabel(pFieldID)
{var vField=document.getElementById(pFieldID);while(vField.tagName.toLowerCase()!="table")
{if(vField.tagName.toLowerCase()=="body"||vField.tagName.toLowerCase()=="")
return;else
vField=vField.parentElement;}
while(vField.tagName.toLowerCase()!="tr")
{if(vField.tagName.toLowerCase()=="body"||vField.tagName.toLowerCase()=="")
return;else
vField=vField.parentElement;}
var vLabel=vField.children[0];if(vLabel.className="fieldlabel")
return vLabel.innerText;else return"FIELD NOT FOUND";}
function getFieldAttribute(pFieldName,pDataContainerRef)
{if(!isCustomField(pFieldName))
{if(pFieldName.indexOf("_HH")!=-1)
{pFieldName=pFieldName.replace("_HH","");}
if(pFieldName.indexOf("_MI")!=-1)
{pFieldName=pFieldName.replace("_MI","");}}
var vDataContainerRef;if(isObject(pDataContainerRef))
{vDataContainerRef=pDataContainerRef;}
else
{vDataContainerRef=gCurrentFieldValues;}
if(!isObject(vDataContainerRef))
{return"";}
vDataAttributesNode=vDataContainerRef.getElementsByTagName("ATTRIBUTES")[0];if(!isObject(vDataAttributesNode))
return"";var vNodeToCheck=vDataAttributesNode.getElementsByTagName(pFieldName)[0];if(isObject(vNodeToCheck)&&isObject(vNodeToCheck.childNodes.item(0))&&vNodeToCheck.childNodes.item(0).nodeType==3)
{return vNodeToCheck.text;}
else
{return"";}}
function setTimeFieldValue(pField,pValue,pDataContainerRef)
{var vField;if(typeof(pField)=="string")
{vField=document.getElementById(pField);}
else
{vField=pField;}
if(!isObject(vField))
{return;}
var vDataContainerRef;if(isObject(pDataContainerRef))
{vDataContainerRef=pDataContainerRef;}
else
{vDataContainerRef=gCurrentFieldValues;}
if(!isObject(vDataContainerRef))
{return;}
var vHourValue
var vMinValue
if(pValue!=""||pValue==0)
{vHourValue=Math.floor(pValue/3600);vMinValue=(pValue%3600)/60;}
else
{vHourValue="";vMinValue="";}
if(vHourValue.toString()!=""&&vHourValue.toString().length<2)
{vHourValue="0"+vHourValue.toString();}
if(vMinValue.toString()!=""&&vMinValue.toString().length<2)
{vMinValue="0"+vMinValue.toString();}
var vHourFieldId=pField+"_HH";var vMinuteFieldId=pField+"_MI";setFieldValue(pField,pValue);setFieldValue(vHourFieldId,vHourValue);setFieldValue(vMinuteFieldId,vMinValue);setFieldValue(vHourFieldId+"_display",vHourValue);setFieldValue(vMinuteFieldId+"_display",vMinValue);}
function syncFieldValue(pFieldName,pFieldValue)
{setFieldValue(pFieldName,pFieldValue,gCurrentFieldValues);setFieldValue(pFieldName,pFieldValue,gLastFieldValues);}
function setFieldValue(pFieldName,pFieldValue,pDataContainerRef,bAlreadyClientFormat)
{if(!isObject(pFieldValue))
{pFieldValue="";}
var vItemValue;if(bAlreadyClientFormat)
vItemValue=pFieldValue;else
vItemValue=convertClientFormatByElementId(pFieldName,pFieldValue);var vDataContainerRef;if(isObject(pDataContainerRef))
{vDataContainerRef=pDataContainerRef;}
else
{vDataContainerRef=gCurrentFieldValues;}
if(!isObject(vDataContainerRef))
{return;}
var vNodeToSet=vDataContainerRef.getElementsByTagName(pFieldName)[0];if(isObject(vNodeToSet)&&vNodeToSet.nodeName!="BUTTONS"&&vNodeToSet.nodeName.indexOf("_list")==-1&&vNodeToSet.nodeName.indexOf("_options")==-1&&vNodeToSet.nodeName.toUpperCase()!="ATTRIBUTES")
{vNodeToSet.text=vItemValue;}
else
{if(pFieldName.indexOf("_options")==-1&&pFieldName.indexOf("_list")==-1)
{var vRoot=vDataContainerRef.ownerDocument;if(vRoot!=null)
{var vNewNode=vRoot.createElement(pFieldName);vNewNode.text=vItemValue;vFirstNode=vDataContainerRef.firstChild;if(isObject(vFirstNode))
{vDataContainerRef.insertBefore(vNewNode,vFirstNode);}
else
{vDataContainerRef.appendChild(vNewNode);}}}}}
function setFieldAttribute(pFieldName,pFieldAttributeValue,pDataContainerRef,pImplementAttrs)
{if(!isObject(pImplementAttrs))
{pImplementAttrs=true;}
if(!isCustomField(pFieldName))
{if(pFieldName.indexOf("_HH")!=-1)
{pFieldName=pFieldName.replace("_HH","");}
if(pFieldName.indexOf("_MI")!=-1)
{pFieldName=pFieldName.replace("_MI","");}}
var vDataContainerRef;if(isObject(pDataContainerRef))
{vDataContainerRef=pDataContainerRef;}
else
{vDataContainerRef=gCurrentFieldValues;}
if(!isObject(vDataContainerRef))
{return;}
vDataAttributesNode=vDataContainerRef.getElementsByTagName("ATTRIBUTES")[0];var vNodeToSet=vDataAttributesNode.getElementsByTagName(pFieldName)[0];if(isObject(vNodeToSet))
{vNodeToSet.text=pFieldAttributeValue;}
else
{var vRoot=vDataContainerRef.ownerDocument;var vNewNode=vRoot.createElement(pFieldName);vNewNode.text=pFieldAttributeValue;vDataAttributesNode.appendChild(vNewNode);}
if(pImplementAttrs)
{implementAttributesAll();}}
function nocontextmenu()
{if(getDebug()==false)
{event.cancelBubble=true
event.returnValue=false;return false;}}
document.oncontextmenu=nocontextmenu;document.onhelp=onlyShowPageHelp;document.attachEvent("onkeydown",noKeyDown);document.attachEvent("onclick",killLastKeyPressed);function onlyShowPageHelp()
{ShowPageHelp();event.cancelBubble=true;event.returnValue=false;return false;}
function setFocusOnMsgButton(vscope)
{var vCell=vscope.document.getElementById("message_button_cell");if(isObject(vCell))
{killLastKeyPressed()
vCell.focus();return true;}}
function showHideDirectSelection()
{var screenName=dstmShowModalDialog("directSelect",null,"status=no;dialogHeight:235px; dialogWidth:550px;resizable:No;dialogTop:'';dialogLeft:'';center:true ");if(isObject(screenName)&&screenName.toUpperCase()!="CANCEL")
{screenName=screenName.toUpperCase();MM_goToSubframe('MAINIFRAME','../work/'+screenName);}}
function noKeyDown()
{var win=getBrowserWindow();if(window.glassTopOn||win.glassTopOn)
{if((window.event.keyCode!=13&&window.event.keyCode!=32&&window.event.keyCode!=9))
{window.event.cancelBubble=true;return false;}
if(window.event.keyCode==9&&isMessageBarOpen(""))
{if(isObject(event.srcElement)&&isObject(event.srcElement.id))
{if(event.srcElement.id.indexOf("message_button_cell")>-1)
return true;}
try
{return setFocusOnMsgButton(getBrowserWindow())}
catch(exception)
{if(window.gMessageInitScope=="popupwindow")
{try
{return setFocusOnMsgButton(window)}
catch(exception)
{}}}}}
if(typeof(gPageName)!="undefined"&&gPageName=='LOGIN')
{return true;}
var isMainWindow=false;if(isObject(win.mainWindow)&&win.mainWindow)
isMainWindow=true;if((event.keyCode==116)||(event.keyCode==112)||(event.keyCode==27)||(event.altKey&&event.keyCode==36)||(event.altKey&&event.keyCode==37)||(event.altKey&&event.keyCode==38)||(event.altKey&&event.keyCode==39)||(event.altKey&&event.keyCode==40)||(event.ctrlKey&&event.keyCode==116)||(event.ctrlKey&&event.keyCode==76))
{if((event.altKey&&event.keyCode==37)||(event.altKey&&event.keyCode==36))
{alert(MSG_ERR_BROWSER_KEYS_DISABLED);}
return killEvent();}
else if(event.ctrlKey&&event.keyCode==113&&isMainWindow&&!isHyperLinkWindow())
{win.showHideDirectSelection();}
else if(event.ctrlKey&&event.keyCode==84&&isMainWindow)
{if(gPageName=="BSSTRT")
loadmain("BSUAED");else
{win.MM_goToSubframe('MAINIFRAME','../base/BSUAED');}
event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.ctrlKey&&event.keyCode==84)
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.ctrlKey&&event.keyCode==72&&isMainWindow)
{ShowHelpTopics();event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.ctrlKey&&event.keyCode==72)
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.ctrlKey&&event.keyCode==66&&isMainWindow)
{ShowAboutWindow();event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.ctrlKey&&event.keyCode==66)
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.altKey&&event.keyCode==81&&isMainWindow&&!event.ctrlKey)
{win.setSystemLogout();event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.altKey&&event.keyCode==81&&!event.ctrlKey)
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.ctrlKey&&event.keyCode==40)
{clickToolbarIcon("NEXTROW",'normalicons');}
else if(event.ctrlKey&&event.keyCode==38)
{clickToolbarIcon("PREVROW",'normalicons');}
else if(event.ctrlKey&&event.keyCode==78)
{clickToolbarIcon("NEW",'normalicons');}
else if(event.ctrlKey&&event.keyCode==68)
{clickToolbarIcon("DELETE",'normalicons');}
else if(event.ctrlKey&&event.keyCode==117)
{clickToolbarIcon("DELETE",'normalicons');}
else if(event.keyCode==115||(event.ctrlKey&&event.keyCode==70))
{clickToolbarIcon("COPY",'normalicons');}
else if(event.keyCode==117)
{clickToolbarIcon("NEW",'normalicons');}
else if(event.ctrlKey&&event.keyCode==83&&isMainWindow)
{var vIconGroup=win.document.getElementById("normalicons");if(vIconGroup.style.display=="block")
clickToolbarIcon("SAVE",'normalicons');else
clickToolbarIcon("EDITOR_SAVE",'editoricons');}
else if(event.keyCode==121)
{clickToolbarIcon("SAVE",'normalicons');}
else if(event.ctrlKey&&event.keyCode==82)
{clickToolbarIcon("RESET",'normalicons');}
else if(event.ctrlKey&&event.keyCode==37)
{if(event.srcElement!=null&&(("INPUT"==event.srcElement.tagName&&event.srcElement.type=='text')||"TEXTAREA"==event.srcElement.tagName))
return true;else
clickToolbarIcon("BACK",'normalicons');}
else if(event.keyCode==122)
{clickToolbarIcon("BACK",'normalicons');}
else if(event.ctrlKey&&event.keyCode==39)
{if(event.srcElement!=null&&(("INPUT"==event.srcElement.tagName&&event.srcElement.type=='text')||"TEXTAREA"==event.srcElement.tagName))
return true;else
clickToolbarIcon("FORWARD",'normalicons');}
else if(event.keyCode==123)
{clickToolbarIcon("FORWARD",'normalicons');}
else if(event.altKey&&event.keyCode==80)
{clickToolbarIcon("PRINT",'normalicons');}
else if(event.altKey&&event.keyCode==72)
{clickToolbarIcon("HELP",'normalicons');}
else if(event.altKey&&event.keyCode==69&&isMainWindow&&!event.ctrlKey)
{var vIconGroup=win.document.getElementById("normalicons");var vButton=win.document.getElementById("editor_design_icon");if(vIconGroup.style.display=="block")
clickToolbarIcon("DESIGN",'normalicons');else if(vButton.style.display=="block")
clickToolbarIcon("EDITOR_DESIGN",'editoricons');else
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}}
else if(event.altKey&&event.keyCode==69&&!event.ctrlKey)
{return killEvent();}
else if(event.ctrlKey&&event.keyCode==69&&!event.altKey)
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.ctrlKey&&event.keyCode==73)
{clickToolbarIcon("EDITOR_EXIT",'editoricons');}
else if(event.ctrlKey&&event.keyCode==79)
{clickToolbarIcon("EDITOR_POPUPSELECT",'editoricons');}
else if(event.ctrlKey&&event.keyCode==71)
{clickToolbarIcon("EDITOR_GROUPSELECT",'editoricons');}
else if(event.ctrlKey&&event.keyCode==87&&isMainWindow)
{var vIconGroup=win.document.getElementById("editoricons");var vButton=win.document.getElementById("editor_preview_icon");if(vIconGroup.style.display=="block"&&vButton.style.display=="block")
clickToolbarIcon("EDITOR_PREVIEW",'editoricons');else
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}}
else if(event.ctrlKey&&event.keyCode==87)
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}
else if(event.ctrlKey&&event.keyCode==65)
{var obj=event.srcElement;if(isObject(obj)&&isObject(obj.id)&&obj.tagName=="INPUT")
{obj.focus();obj.select();if(obj.type!="file")
{event.keyCode=0;}
event.cancelBubble=true;event.returnValue=false;return false;}}
else if(event.ctrlKey&&event.keyCode==77)
{var vmain=getBrowserWindow();if(vmain)
{vmain.selectFirstMenu();return false;}}
else if(event.altKey&&event.keyCode==67)
{var vframe=isSubIframeWindow()||window.clearHeaderFilterFields?this:getSubIframeWindow();if(vframe&&vframe.clearHeaderFilterFields)
vframe.clearHeaderFilterFields();}
else if(event.keyCode==119)
{if(isSubIframeWindow()||window.runQuickQuery)
{if(window.runQuickQuery)
{try
{window.document.body.focus();}
catch(exception)
{}
setTimeout("runQuickQuery()",100);}}
else
{var vsubiframe=getSubIframeWindow();if(vsubiframe&&vsubiframe.runQuickQuery)
vsubiframe.runQuickQuery();}}
else if(event.keyCode==118)
{if(window.gPageName=="OSEQPP"&&isObject(window.isListVisible)&&!window.isListVisible())
return;var vsubiframe=isSubIframeWindow()||window.toggleHeaderFilterRow?this:getSubIframeWindow();if(vsubiframe&&vsubiframe.toggleHeaderFilterRow)
vsubiframe.toggleHeaderFilterRow();}
else if(event.keyCode==8)
{var obj=event.srcElement;if(isObject(obj)&&((obj.tagName=="INPUT"&&obj.type!=="checkbox")||obj.tagName=="SELECT"||(obj.tagName=="TEXTAREA"&&!obj.readOnly)))
{event.cancelBubble=true;return true;}
else
{event.keyCode=0;event.cancelBubble=true;event.returnValue=false;return false;}}
lastKeyPressedCode=event.keyCode
checkTabKey();}
function selectFirstMenu()
{try
{g_main.MainMenu.items[0].dispatchAction();}
catch(e)
{}}
function isSubIframeWindow()
{if(window.name==null)
return false;return window.name.indexOf("SUBIFRAME")>-1;}
function completeClickToolbarIcon(pName,pIconGroup)
{if(isObject(gProcessEventTimer))
{gSaveSubmitTimer=window.setTimeout("completeClickToolbarIcon('"+pName+"','"+pIconGroup+"')",500);return;}
var mainFrame=getMainIframeWindow()
if(isObject(mainFrame)&&isObject(mainFrame.gProcessEventTimer))
{gSaveSubmitTimer=window.setTimeout("completeClickToolbarIcon('"+pName+"','"+pIconGroup+"')",500);return;}
var subFrame=getSubIframeWindow()
if(isObject(subFrame)&&(isObject(subFrame.gProcessEventTimer)||subFrame.gLookupRunning))
{gSaveSubmitTimer=window.setTimeout("completeClickToolbarIcon('"+pName+"','"+pIconGroup+"')",500);return;}
if(pName=='SAVE')
{var vMessageBar=getBrowserWindow().document.getElementById("messagebar");if(typeof(vMessageBar)!="undefined"&&vMessageBar!=null)
{if(vMessageBar.style.display=="block")
{if(gQuestionInMessages||getBrowserWindow().gQuestionInMessages)
{gSaveSubmitTimer=window.setTimeout("completeClickToolbarIcon('"+pName+"','"+pIconGroup+"')",500);return;}
if(gErrorInMessages||getBrowserWindow().gErrorInMessages)
return;}}}
else if(getBrowserWindow().messageBarOpen())
return;var win=getBrowserWindow();var vButton=win.document.getElementById(pName);var vIconGroup=win.document.getElementById(pIconGroup);if(isObject(vButton)&&vIconGroup.style.display=="block")
{win.setglasstop();win.showStatusMeter();win.setTimeout("completeClickFireEvent('"+pName+"')",100);}}
function completeClickFireEvent(pName)
{hideStatusMeter();removeglasstop();var vButton=document.getElementById(pName);vButton.fireEvent("onclick");}
function resetFocusOnMain()
{try
{var subFrame=getSubIframeWindow()
subFrame.document.activeElement.focus();}
catch(e){}}
function clickToolbarIcon(pName,pIconGroup)
{try
{document.body.focus();var subFrame=getSubIframeWindow()
if(isObject(subFrame)&&isObject(subFrame.gCurrentFocusField))
{if(pName=="SAVE")
{window.setTimeout("completeClickToolbarIcon('"+pName+"','"+pIconGroup+"')",100);if(window.event!=null)
{window.event.keyCode=0;window.event.cancelBubble=true;window.event.returnValue=false;return false;}}}
else
document.body.focus();var win=getBrowserWindow();var vButton=win.document.getElementById(pName);var vIconGroup=win.document.getElementById(pIconGroup);if(isObject(vButton)&&(isblank(vButton.style.display)||vButton.style.display=="block")&&!vButton.disabled&&vIconGroup.style.display=="block")
{vButton.fireEvent("onclick");}
if(window.event!=null)
{window.event.keyCode=0;window.event.cancelBubble=true;window.event.returnValue=false;return false;}}
catch(e)
{if(window.event!=null)
{window.event.keyCode=0;window.event.cancelBubble=true;window.event.returnValue=false;return false;}}}
function attachCustomEvent(pObject,pEventName,pEventString)
{var vObject;if(typeof(pObject)=="string")
{vObject=document.getElementById(pObject);}
else
{vObject=pObject;}
var vEventString=";"+pEventString;if(isObject(vObject))
eval("vObject."+pEventName+" += '"+vEventString+"'");}
function detachCustomEvent(pObject,pEventName,pEventString)
{var vObject;if(typeof(pObject)=="string")
{vObject=document.getElementById(pObject);}
else
{vObject=pObject;}
var vEventText=eval("vObject."+pEventName);vEventText=vEventText.replace(pEventString,"");eval("vObject."+pEventName+" = vEventText");}
function fireCustomEvent(pObject,pEventName)
{var vObject;if(typeof(pObject)=="string")
{vObject=document.getElementById(pObject);}
else
{vObject=pObject;}
eval("eval(vObject."+pEventName+")");}
function getBrowserWindow()
{var vCount=0;var vWindow=window;while(vCount<20)
{if(eval("vWindow.mainWindow")==null&&!vWindow.mainWindow)
{vWindow=vWindow.parent;vCount++;}
else
{break;}}
return vWindow;}
function getMainIframeWindow()
{if(gActiveMainIframe!=null)
return gActiveMainIframe.contentWindow;var vWindow=getBrowserWindow();var vIframeRef=vWindow.document.getElementById("MAINIFRAME");if(vIframeRef!=null)
return vIframeRef.contentWindow;else
return null;}
function getSubIframeWindow()
{var vWindow=getBrowserWindow();var vMainIframe=getMainIframeWindow();if(vMainIframe==null)
return false;if(vMainIframe.gActiveSubIFrame!=null)
return vMainIframe.gActiveSubIFrame.contentWindow;var vIframeRef=vMainIframe.document.getElementById("SUBIFRAME");if(isObject(vIframeRef))
{return vIframeRef.contentWindow;}
else
{return false;}}
function getNamedIframeWindow(pName)
{if(pName.toUpperCase()=="MAINIFRAME"||pName=="CLONEMAINIFRAME")
{return getMainIframeWindow();}
if(pName==""||pName=="Home_Page")
{return getBrowserWindow();}
if(pName=="CLONESUBIFRAME")
pName="SUBIFRAME";var vWindow=getBrowserWindow();var vMainIframe=null;var vIframeRef=null;try{vMainIframe=getMainIframeWindow();vIframeRef=vMainIframe.document.getElementById(pName);}catch(exception){}
if(isObject(vIframeRef))
{return vIframeRef.contentWindow;}
else
{vIframeRef=vWindow.document.getElementById(pName);if(isObject(vIframeRef))
{return vIframeRef.contentWindow;}
else
{var subiframe=getSubIframeWindow();if(subiframe)
{vIframeRef=subiframe.document.getElementById(pName);if(isObject(vIframeRef))
return vIframeRef.contentWindow;}
return false;}}}
function checkCustomFieldValue(vNode)
{if(isCustomField(vNode.nodeName))
{var vFld=document.getElementById(vNode.nodeName);if(vFld==null)
return;if(vFld.value!=vNode.text)
{vFld.value=vNode.text;}}}
function checkAllCustomFieldValues()
{if(isObject(window.getCustomFieldNamesToPost))
{var cfarray=getCustomFieldNamesToPost(true,true)
for(var i=0;i<cfarray.length;i++)
{var vfobj=document.getElementById(cfarray[i]);if(vfobj!=null)
{var nodevalue=getFieldValue(cfarray[i]);if(nodevalue!=vfobj.value)
vfobj.value=nodevalue;}}}}
function checkAllTextAreaMaxLength()
{var vError=false;var textAreas=window.document.getElementsByTagName("textarea");for(var i=0;i<textAreas.length;i++)
{vErrorFound=checkTextAreaMaxLength(textAreas[i]);if(vErrorFound)
{vError=true;}}
return vError;}
function checkTextAreaMaxLength(pElem,selectAll)
{clearDisplayFieldMessage(pElem.id)
var vElem;if(typeof(pElem)=="string")
{vElem=document.getElementById(pElem);}
else
{vElem=pElem;}
var vMax=vElem.maxlength;if(isObject(vMax)&&vMax!="")
{var valueText=vElem.value;var adjust=0;if(valueText.length>0)
{valueText=escape(valueText);valueText=valueText.replace(/%0D%0A/g,"A")
valueText=unescape(valueText);}
if(selectAll!=null&&!selectAll)
adjust=1;if(valueText.length+adjust>vMax)
{if(selectAll==null||selectAll)
{displayMessageOnField(vElem.id,BASE_TEXTTOOLONG);vElem.select();}
return true;}}
return false;}
function checkTextAreaCharacter(pObject)
{var vChar=String.fromCharCode(window.event.keyCode)
if(checkTextAreaMaxLength(pObject,false))
{var vSel=document.selection;var vLen=0;if(isObject(vSel)&&vSel.type.toLowerCase()=="text")
{vRng=vSel.createRange();if(isObject(vRng))
{vLen=vRng.htmlText.length;}}
if(vLen<=0)
{if(pObject.value.length>=pObject.maxlength)
{event.returnValue=false;soundError();return;}}}}
function getGridSelectedRows(tableName)
{var vGridTb=document.all(tableName);if(vGridTb==null)
throw new Error("debug :Error getGridSelectedRows. Make sure to provide the rigth table name")
var vGridDataSrc=vGridTb.dataSrc;if(vGridDataSrc==null)
throw new Error("debug :Error getGridSelectedRows. The grid table in not data bound")
vGridDataSrc=vGridDataSrc.replace("#","");var vGridDataSrcObj=document.all(vGridDataSrc);if(vGridDataSrcObj==null)
throw new Error("debug :Error getGridSelectedRows. The xml data is land does not exist")
if(vGridTb.gSelectedItemsArray==null||vGridTb.gSelectedItemsArray.length==0)
return null;var vGridSRows=new Array();vGridTb.gSelectedItemsArray.sort()
var vLen=vGridTb.gSelectedItemsArray.length
var vXmlRows=vGridDataSrcObj.getElementsByTagName("ROW");var vReturnVal="<ROOT>"
var vTableRow=null
for(i=0;i<vLen;i++)
vGridSRows[i]=vXmlRows[parseInt(vGridTb.gSelectedItemsArray[i],10)]
for(i=0;i<vGridSRows.length;i++)
vReturnVal=vReturnVal+vGridSRows[i].xml
vReturnVal=vReturnVal+"</ROOT>"
return vReturnVal}
function childFrameButtonClicked(action,checkForSave)
{if(checkForSave==null)
checkForSave=true;var objSub=document.getElementById("MAINIFRAME");if(action=="HELP")
{ShowPageHelp();}
else if(action=="BACK")
{if(checkForSave&&isObject(eval("objSub.contentWindow.childFrameHasChanges")))
{if(objSub.contentWindow.childFrameHasChanges()&&saveChangesOnForm(objSub.contentWindow.gPageName))
{confirmStandardMessage("completeBack");event.cancelBubble=true;event.returnValue=false;return false;}}
doMainFrameBack()}
else if(action=="FORWARD")
{if(checkForSave&&isObject(eval("objSub.contentWindow.childFrameHasChanges")))
{if(objSub.contentWindow.childFrameHasChanges()&&saveChangesOnForm(objSub.contentWindow.gPageName))
{confirmStandardMessage("completeForward");event.cancelBubble=true;event.returnValue=false;return false;}}
doMainFrameForward()}
else
{if(objSub!=null&&eval("objSub.contentWindow.clickedButton")!=null)
objSub.contentWindow.clickedButton(action);}}
function completeForward(buttonClicked)
{if(buttonClicked==null)
buttonClicked=1;var obj=document.getElementById("MAINIFRAME");if(buttonClicked==1)
{if(isObject(eval("obj.contentWindow.saveTabContents"))&&!obj.contentWindow.saveTabContents(true))
return;gMainFrameTimer=window.setTimeout("goMainFrameForwardTimeout()",5);return;}
if(buttonClicked==-1)
{return;}
if(buttonClicked==0)
{doMainFrameForward()}}
function doMainFrameForward()
{var objRef=new Object();objRef.sendhtmlto="MAINIFRAME";objRef.controltype="nonmodal";var vcommon=document.getElementById("MAINIFRAME");var vQueryString=vcommon.contentWindow.getKeyFieldValuesForForward();processEventCore(objRef,"../base/FORWARD",vQueryString);}
function goMainFrameForwardTimeout()
{var obj=document.getElementById("MAINIFRAME");if(isObject(eval("obj.contentWindow.gActiveSubIFrame"))&&isObject(obj.contentWindow.gActiveSubIFrame.contentWindow.gProcessEventTimer))
{gMainFrameTimer=window.setTimeout("goMainFrameForwardTimeout()",5);}
else if(isObject(obj.contentWindow.gProcessEventTimer))
{gMainFrameTimer=window.setTimeout("goMainFrameForwardTimeout()",5);}
else
{gMainFrameTimer=null;if(obj.contentWindow.checkMessageBar())
{doMainFrameForward()}}}
function completeBack(buttonClicked)
{if(buttonClicked==null)
buttonClicked=1;var obj=document.getElementById("MAINIFRAME");if(buttonClicked==1)
{if(isObject(eval("obj.contentWindow.saveTabContents"))&&!obj.contentWindow.saveTabContents(true))
return;gMainFrameTimer=window.setTimeout("goMainFrameBackTimeout()",5);return;}
if(buttonClicked==-1)
{return;}
if(buttonClicked==0)
{doMainFrameBack()}}
var backTimerVariable;function doMainFrameBack()
{displayPreviousPage();}
function checkEventProgress(action)
{if(isObject(gProcessEventTimer))
{backTimerVariable=window.setTimeout("checkEventProgress(action)",5);}
else
{eval(action);}}
function displayPreviousPage()
{var lastpage=getPreviousPage();if(!isObject(lastpage))
return;sysfuncname=lastpage.sysfunc;userfuncname=lastpage.userfunc;if(sysfuncname!=null&&sysfuncname!="")
{gLastPageName=gPageName;gLastUserFunction=gUserFunctionName;gPageName=sysfuncname;gUserFunctionName=userfuncname;gCurrentTab="";gPageMode="";if(!isblank(gPageName)&&gPageName=="startpage")
{displayStartCenter("",false);}
else
{var obj=null;if(lastpage.cacheScreen)
obj=makeMainIframe(gUserFunctionName);else
obj=makeMainIframe("_ALL");if(useCachedScreen(obj)&&lastpage.cacheScreen)
{showHideMainIframes(true);toggleMainPageStartCenter(false);}
else
{obj.sendhtmlto="MAINIFRAME";obj.controltype="nonmodal"
processEventCore(obj,"../base/BACK?lastuserfunc="+userfuncname,"");if(gErrorInMessages)
{gPageName=gLastPageName;gUserFunctionName=gLastUserFunction;restoreLastMainIframe();}
else
{showHideMainIframes(false);toggleMainPageStartCenter(false);}}}}}
function goMainFrameBackTimeout()
{var obj=document.getElementById("MAINIFRAME");if(isObject(eval("obj.contentWindow.gActiveSubIFrame"))&&isObject(obj.contentWindow.gActiveSubIFrame.contentWindow.gProcessEventTimer))
{gMainFrameTimer=window.setTimeout("goMainFrameBackTimeout()",5);}
else if(isObject(obj.contentWindow.gProcessEventTimer))
{gMainFrameTimer=window.setTimeout("goMainFrameBackTimeout()",5);}
else
{gMainFrameTimer=null;if(obj.contentWindow.checkMessageBar())
{doMainFrameBack()}}}
function setIframeSize(pIframeName)
{var vIframe=document.getElementById(pIframeName);var vWindowHeight=document.body.clientHeight+(document.body.clientTop*2);var vOffsetHeight=0;var vOffsetObject=vIframe;while(vOffsetObject.offsetParent.tagName.toLowerCase()!="body")
{vOffsetHeight=vOffsetHeight+vOffsetObject.offsetTop;vOffsetObject=vOffsetObject.offsetParent;}
vOffsetHeight=vOffsetHeight+vOffsetObject.offsetTop;var vBrowserWindowContentHeight=vOffsetHeight+(document.body.clientTop*2);var vIframeHeightToSet=vWindowHeight-vBrowserWindowContentHeight;vIframe.height=vIframeHeightToSet-10;}
function displayQuestionMessageOnPage(pMessageNum,pQuestionType,pHandler,pMessage,pArgArray,pScope)
{var vMessageXml=buildMessageXML(0,"question",pQuestionType,pHandler,null,null,null,pMessage,pArgArray,pScope);sendMessageXmlToDataIsland(vMessageXml);}
function createPageMessageButtons(pBtn1,pBtn2,pBtn3,pScope)
{var vBtn1Arry=getPageMessageButtonConfig(pBtn1);if(vBtn1Arry)
{var vBtn1Text=vBtn1Arry[0];var vBtn1Click=vBtn1Arry[1];}
var vBtn2Arry=getPageMessageButtonConfig(pBtn2);if(vBtn2Arry)
{var vBtn2Text=vBtn2Arry[0];var vBtn2Click=vBtn2Arry[1];}
var vBtn3Arry=getPageMessageButtonConfig(pBtn3);if(vBtn3Arry)
{var vBtn3Text=vBtn3Arry[0];var vBtn3Click=vBtn3Arry[1];}
var vButtonCell=document.getElementById("message_button_cell");var vButtonCell2=document.getElementById("message_button_cell2");var vButtonCell3=document.getElementById("message_button_cell3");if(!isObject(gPageMessageButtonTemplate))
{gPageMessageButtonTemplate=vButtonCell.innerHTML}
vButtonCell.detachEvent("onkeydown",messageBarButtonOnkeydown);vButtonCell2.detachEvent("onkeydown",messageBarButtonOnkeydown);vButtonCell3.detachEvent("onkeydown",messageBarButtonOnkeydown);vButtonCell.tabIndex=4001;vButtonCell2.tabIndex=4002;vButtonCell3.tabIndex=4003;vButtonCell.attachEvent("onkeydown",messageBarButtonOnkeydown);vButtonCell2.attachEvent("onkeydown",messageBarButtonOnkeydown);vButtonCell3.attachEvent("onkeydown",messageBarButtonOnkeydown);vButtonCell.innerHTML="";vButtonCell2.innerHTML="";vButtonCell3.innerHTML="";var vButton1HTML="";var vButton2HTML="";var vButton3HTML="";if(vBtn1Arry)
{vButton1HTML=makePageButtonHTML("PageMessageButton1",vBtn1Text,vBtn1Click,pScope);vButtonCell.innerHTML=vButton1HTML;}
if(vBtn2Arry)
{vButton2HTML=makePageButtonHTML("PageMessageButton2",vBtn2Text,vBtn2Click,pScope);vButtonCell2.innerHTML=vButton2HTML;vButtonCell.lastButton=false;}
else
{vButtonCell.lastButton=true;}
if(vBtn3Arry)
{vButton3HTML=makePageButtonHTML("PageMessageButton3",vBtn3Text,vBtn3Click,pScope);vButtonCell3.innerHTML=vButton3HTML;vButtonCell3.lastButton=true;vButtonCell2.lastButton=false;}
else
{vButtonCell2.lastButton=true;vButtonCell3.lastButton=false;}}
function makePageButtonHTML(pBtnId,pBtnText,pBtnAction,pScope)
{var vButtonHTML=gPageMessageButtonTemplate;while(vButtonHTML.indexOf("MessageButtonID")!=-1)
{vButtonHTML=vButtonHTML.replace("MessageButtonID",pBtnId);}
if(isObject(pBtnAction)&&pBtnAction!="")
{while(vButtonHTML.indexOf("MessageButtonAction")!=-1)
{vButtonHTML=vButtonHTML.replace("MessageButtonAction","getNamedIframeWindow('"+pScope+"')."+pBtnAction+";messageBarReset()");}}
else
{while(vButtonHTML.indexOf("MessageButtonAction")!=-1)
{vButtonHTML=vButtonHTML.replace("MessageButtonAction","messageBarReset()");}}
while(vButtonHTML.indexOf("ButtonText")!=-1)
{vButtonHTML=vButtonHTML.replace("ButtonText",pBtnText);}
return vButtonHTML;}
function getPageMessageButtonConfig(pBtn)
{if(isObject(pBtn)&&pBtn!="")
{if(typeof(pBtn)=="string")
{pBtn=pBtn.split(",");}
var vBtnText;var vBtnClick;if(isObject(pBtn[0]))
{vBtnText=pBtn[0];}
if(isObject(pBtn[1]))
{vBtnClick=pBtn[1];}
var vReturnArry=new Array(2);vReturnArry[0]=vBtnText;vReturnArry[1]=vBtnClick;return vReturnArry;}
else
{return false;}}
function createPageMessageText(pMessageNum,pType,pMessage)
{var vMessageBar=document.getElementById("messagebar");var vMessageContainterCell=document.getElementById("message_container_cell");var vNewErrorTable=document.createElement("table");vNewErrorTable.id="message_table_"+pMessageNum;vNewErrorTable.width="100%";vNewErrorTable.style.display="block";vNewErrorTable.className="messagebarbody";vNewErrorTable.cellspacing="2";vNewErrorTable.cellpadding="2";var vNewErrorTableBody=document.createElement("tbody");vNewErrorTable.appendChild(vNewErrorTableBody);var vNewErrorTableRow=document.createElement("tr");vNewErrorTableRow.id="message_table_row_"+pMessageNum;vNewErrorTableBody.appendChild(vNewErrorTableRow);var vNewErrorTableImageCell=document.createElement("td");vNewErrorTableImageCell.id="message_table_image_cell_"+pMessageNum;vNewErrorTableRow.appendChild(vNewErrorTableImageCell);var vNewErrorTableImage=document.createElement("img");vNewErrorTableImage.id="message_table_image_"+pMessageNum;vNewErrorTableImageCell.appendChild(vNewErrorTableImage);var vNewErrorTableTextCell=document.createElement("td");vNewErrorTableTextCell.id="message_Table_Text_"+pMessageNum;vNewErrorTableTextCell.nowrap=true;vNewErrorTableTextCell.width="100%";vNewErrorTableTextCell.valign="middle";vNewErrorTableTextCell.className="messagebartext";vNewErrorTableRow.appendChild(vNewErrorTableTextCell);var re=/&#44;/g
pMessage=pMessage.replace(re,",");if(pMessage.indexOf("\\n")>=0){pMessage=unescape(pMessage);var regex=/\\n/g
pMessage=pMessage.replace(regex,"<BR>");vNewErrorTableTextCell.innerHTML=pMessage;}
else{if(pMessage.indexOf("href=")==-1)
vNewErrorTableTextCell.innerText=unescape(pMessage);else
vNewErrorTableTextCell.innerHTML=unescape(pMessage);}
vMessageContainterCell.appendChild(vNewErrorTable);gErrorInMessages=false;switch(pType)
{case"error":gErrorInMessages=true;gProcessEventActionType=null;vNewErrorTableImage.src=window.gStylePrePath+window.gStyleCD+"/bar_error.gif";break;case"confirm":vNewErrorTableImage.src=window.gStylePrePath+window.gStyleCD+"/bar_confirm.gif";break;case"warning":gWarningInMessages=true;vNewErrorTableImage.src=window.gStylePrePath+window.gStyleCD+"/bar_alert.gif";break;case"information":gInfoInMessages=true;vNewErrorTableImage.src=window.gStylePrePath+window.gStyleCD+"/bar_info.gif";break;case"question":gQuestionInMessages=true;vNewErrorTableImage.src=window.gStylePrePath+window.gStyleCD+"/bar_question.gif";break;default:gInfoInMessages=true;vNewErrorTableImage.src=window.gStylePrePath+window.gStyleCD+"/bar_info.gif";break;}}
function timeFieldMinuteCharValidator()
{var vChar=String.fromCharCode(window.event.keyCode)
var vPattern=new RegExp("[0123456789-]")
if(!vPattern.test(vChar))
{event.returnValue=false;soundError();return;}}
function timeFieldMinuteValidator()
{var vCurrentCodeFieldDDLB=event.srcElement;var vClickedElement=document.activeElement
if(vCurrentCodeFieldDDLB==vClickedElement)
{return;}
if(vClickedElement)
{while(vClickedElement.tagName=='TD'||vClickedElement.tagName=='TR'||vClickedElement.tagName=='TBODY')
{vClickedElement=vClickedElement.parentElement}}
else
{return;}
if(vClickedElement==gTableObjectDDLB||vClickedElement==document.getElementById(vCurrentCodeFieldDDLB.name+'_arrow')||vClickedElement==document.getElementById(vCurrentCodeFieldDDLB.name+'_options'))
{return;}
if(isObject(vCurrentCodeFieldDDLB))
{var vFieldName=vCurrentCodeFieldDDLB.id;var vFieldValue=getFieldValue(vFieldName);if(isObject(vFieldValue)&&vFieldValue!="")
{var vPattern=new RegExp("^\\d+$")
if(!vPattern.test(vFieldValue))
{displayMessageOnField(vFieldName,MSG_ERR_TIMEMINUTE_VALIDATION_FAILED);soundError();setFieldFocus(vCurrentCodeFieldDDLB.id)
event.returnValue=false;return false;}
if(parseInt(vFieldValue,10)<0||parseInt(vFieldValue,10)>59)
{displayMessageOnField(vFieldName,MSG_ERR_TIMEMINUTE_VALIDATION_FAILED);soundError();setFieldFocus(vCurrentCodeFieldDDLB.id);event.returnValue=false;return false;}}}}
function buildPageMessageAttribute(pIslandRef,pAttName,pValue)
{var vAttribute=pIslandRef.createAttribute(pAttName);if(isObject(pValue))
{vAttribute.value=pValue;}
else
{vAttribute.value="";}
return vAttribute;}
function getPageMessageAttribute(pArrtibuteNode,pAttName)
{var vAttributeItem=pArrtibuteNode.getNamedItem(pAttName)
if(isObject(vAttributeItem)&&vAttributeItem.value!="")
{return vAttributeItem.value}
else
{return null;}}
function validateAndRebuildPageMessageXML(pMessagesXml)
{var vOutPutXmlIsland=new ActiveXObject("MSXML.DOMDocument");var vOutPutString="<MESSAGE>";var vNewMessagesIsland;if(typeof(pMessagesXml)=="string")
{vNewMessagesIsland=new ActiveXObject("MSXML.DOMDocument");vNewMessagesIsland.loadXML(pMessagesXml);}
else
{vNewMessagesIsland=pMessagesXml;}
var vMessageRoot=vNewMessagesIsland.getElementsByTagName("MESSAGE")[0];for(var i=0;i<vMessageRoot.childNodes.length;i++)
{var vCurrentMessageAttributesNode=vMessageRoot.childNodes.item(i).attributes;var vCurrentMessageScope=getPageMessageAttribute(vCurrentMessageAttributesNode,"scope");if(vCurrentMessageScope=="field")
{var reffield=getPageMessageAttribute(vCurrentMessageAttributesNode,"field");if(reffield!=null&document.getElementById(reffield)==null)
vCurrentMessageScope="page";}
if(vCurrentMessageScope=="page")
{var vCurrentMessageType=getPageMessageAttribute(vCurrentMessageAttributesNode,"type");var vCurrentMessageText=getPageMessageAttribute(vCurrentMessageAttributesNode,"label");var vArgs=getPageMessageAttribute(vCurrentMessageAttributesNode,"labelargs");if(vCurrentMessageType=="question")
{var vCurrentMessageQuestionType=getPageMessageAttribute(vCurrentMessageAttributesNode,"questiontype");var vHandlerScope=getPageMessageAttribute(vCurrentMessageAttributesNode,"handlerscope");if(!isObject(vHandlerScope))
{vHandlerScope=window.name;}
if(vCurrentMessageQuestionType.toLowerCase()!="custom")
{var vHandler=getPageMessageAttribute(vCurrentMessageAttributesNode,"handler");}
else
{var vBtn1=getPageMessageAttribute(vCurrentMessageAttributesNode,"button1");var vBtn2=getPageMessageAttribute(vCurrentMessageAttributesNode,"button2");var vBtn3=getPageMessageAttribute(vCurrentMessageAttributesNode,"button3");}}
var vBuiltXML=buildMessageXML(0,vCurrentMessageType,vCurrentMessageQuestionType,vHandler,vBtn1,vBtn2,vBtn3,vCurrentMessageText,vArgs,vHandlerScope);vOutPutString+=vBuiltXML.getElementsByTagName("MESSAGE_ITEM")[0].xml;}
else
{vOutPutString+=vMessageRoot.childNodes.item(i).xml;}}
vOutPutString+="</MESSAGE>";vOutPutXmlIsland.loadXML(vOutPutString);return vOutPutXmlIsland;}
function buildMessageXML(pMessageNum,pType,pQuestionType,pHandler,pBtn1,pBtn2,pBtn3,pMessage,pArgArray,pHandlerScope)
{var vNewMessagesIsland=new ActiveXObject("MSXML.DOMDocument");vNewMessagesIsland.loadXML("<MESSAGE></MESSAGE>");vRoot=vNewMessagesIsland.documentElement;var newMessageItem=vNewMessagesIsland.createElement("MESSAGE_ITEM");vRoot.appendChild(newMessageItem);newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"type",pType));newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"scope","page"));newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"label",pMessage));if(isObject(pArgArray)&&typeof(pArgArray)!="string")
{for(var x=0;x<pArgArray.length;x++)
{var re=/,/g
pArgArray[x]=pArgArray[x].replace(re,"&#44;");}
pArgArray=pArgArray.toString();}
newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"labelargs",pArgArray));if(pType.toLowerCase()=="question")
{newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"questiontype",pQuestionType));var vHandlerScope;if(isObject(pHandlerScope))
{vHandlerScope=pHandlerScope;}
else
{vHandlerScope=window.name;}
newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"handlerscope",vHandlerScope));if(pQuestionType.toLowerCase()!="custom")
{newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"handler",pHandler));}
else
{newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"button1",pBtn1));newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"button2",pBtn2));newMessageItem.setAttributeNode(buildPageMessageAttribute(vNewMessagesIsland,"button3",pBtn3));}}
return vNewMessagesIsland;}
function sendMessageXmlToDataIsland(pMessagesXml)
{var vMessagesIsland=document.getElementById("MESSAGE").XMLDocument;var vNewMessagesIsland;if(typeof(pMessagesXml)=="string")
{vNewMessagesIsland=new ActiveXObject("MSXML.DOMDocument");vNewMessagesIsland.loadXML(pMessagesXml);}
else
{vNewMessagesIsland=pMessagesXml;}
var vMessageRoot=vMessagesIsland.getElementsByTagName("MESSAGE")[0];var vRootFound=true;if(!isObject(vMessageRoot))
{vMessageRoot=vMessagesIsland.createElement("MESSAGE");vRootFound=false;}
var vNewMessageRoot=vNewMessagesIsland.getElementsByTagName("MESSAGE")[0];for(var i=0;i<vNewMessageRoot.childNodes.length;i++)
{gMessageInitProcessing=true;var vCurrentNewMessage=vNewMessageRoot.childNodes.item(i).cloneNode(true);vMessageRoot.appendChild(vCurrentNewMessage);}
if(!vRootFound)
{vMessagesIsland.appendChild(vMessageRoot);}
gMessageInitProcessing=false;implementMessages();}
function transferMessagesToParent()
{var vMessagesIsland=document.getElementById("MESSAGE").XMLDocument;var vParentWindow=window.parent;var vXmlString=vMessagesIsland.xml;var vMessageRoot=vMessagesIsland.getElementsByTagName("MESSAGE")[0];if(isObject(vMessageRoot))
{for(var i=vMessageRoot.childNodes.length-1;i>-1;i--)
{gMessageInitProcessing=true;vMessageRoot.removeChild(vMessageRoot.childNodes.item(i));}}
gMessageInitProcessing=false;vParentWindow.sendMessageXmlToDataIsland(vXmlString);}
function renderStandardMessageOnPage(pMessageNum,pType,pMessage,pArgArray)
{var i
var vPos
if(pMessage&&typeof(pMessage)!="undefined")
{for(i=1;i<11;i++)
{vPos=pMessage.toLowerCase().indexOf(":param"+i.toString())
if(vPos!=-1)
{vArgValue="";if(pArgArray.length>=i-1)
{vArgValue=pArgArray[i-1];}
pMessage=pMessage.substring(0,vPos)+vArgValue+pMessage.substring(vPos+7)}}}
createPageMessageText(pMessageNum,pType,pMessage)
createPageMessageButtons(MESSAGEOKBUTTON,null)
soundFile(SOUND_PAGE_ERROR);if(gPreLoadMessageOnly)
gPreLoadMessageOnly=false;else
OpenMessageBar();}
function renderQuestionMessageOnPage(pMessageNum,pQuestionType,pHandler,pMessage,pArgArray,pScope)
{if(!isObject(pScope)||pScope=="")
{pScope=window.name;}
switch(pQuestionType)
{case"YesNo":renderCustomQuestionMessageOnPage(pMessageNum,MESSAGE_YES_BUTTON+","+pHandler+"(1)",MESSAGE_NO_BUTTON+","+"noCancelWrapper('"+pHandler+"(0)')",null,pMessage,pArgArray,pScope)
break;case"YesNoCancel":renderCustomQuestionMessageOnPage(pMessageNum,MESSAGE_YES_BUTTON+","+pHandler+"(1)",MESSAGE_NO_BUTTON+","+"noCancelWrapper('"+pHandler+"(0)')",MESSAGE_CANCEL_BUTTON+","+"noCancelWrapper('"+pHandler+"(-1)')",pMessage,pArgArray,pScope)
break;case"YesCancel":renderCustomQuestionMessageOnPage(pMessageNum,MESSAGE_YES_BUTTON+","+pHandler+"(1)",MESSAGE_CANCEL_BUTTON+","+"noCancelWrapper('"+pHandler+"(-1)')",null,pMessage,pArgArray,pScope)
break;case"ConfirmSave":renderCustomQuestionMessageOnPage(pMessageNum,MESSAGE_SAVE_BUTTON+","+pHandler+"(1)",MESSAGE_NOSAVE_BUTTON+","+"noCancelWrapper('"+pHandler+"(0)')",MESSAGE_CANCEL_BUTTON+","+"noCancelWrapper('"+pHandler+"(-1)')",pMessage,pArgArray,pScope)
break;case"ServerYesNo":renderCustomQuestionMessageOnPage(pMessageNum,MESSAGE_YES_BUTTON+",serverQuestionHandler(1)",MESSAGE_NO_BUTTON+",serverQuestionHandler(0)",null,pMessage,pArgArray,pScope)
break;case"ServerYesNoCancel":renderCustomQuestionMessageOnPage(pMessageNum,MESSAGE_YES_BUTTON+",serverQuestionHandler(1)",MESSAGE_NO_BUTTON+",serverQuestionHandler(0)",MESSAGE_CANCEL_BUTTON+",serverQuestionHandler(-1)",pMessage,pArgArray,pScope)
break;case"ServerConfirmSave":renderCustomQuestionMessageOnPage(pMessageNum,MESSAGE_SAVE_BUTTON+",serverSaveQuestionHandler(1)",MESSAGE_NOSAVE_BUTTON+",serverSaveQuestionHandler(0)",MESSAGE_CANCEL_BUTTON+",serverSaveQuestionHandler(-1)",pMessage,pArgArray,pScope)
break;default:break;}}
function renderCustomQuestionMessageOnPage(pMessageNum,pBtn1,pBtn2,pBtn3,pMessage,pArgArray,pScope)
{if(!isObject(pScope)||pScope=="")
{pScope=window.name;}
var i
var vPos
if(pMessage&&typeof(pMessage)!="undefined")
{for(i=1;i<11;i++)
{vPos=pMessage.toLowerCase().indexOf(":param"+i.toString())
if(vPos!=-1)
{vArgValue="";if(pArgArray.length>=i-1)
{vArgValue=pArgArray[i-1];}
pMessage=pMessage.substring(0,vPos)+vArgValue+pMessage.substring(vPos+7)}}}
createPageMessageText(pMessageNum,"question",pMessage,pScope)
createPageMessageButtons(pBtn1,pBtn2,pBtn3,pScope);OpenMessageBar();soundFile(SOUND_PAGE_ERROR)}
function checkForQueuedQuestions()
{var vXmlDoc=document.getElementById("MESSAGE").XMLDocument;var vMessageContainerNode=vXmlDoc.documentElement;for(var i=0;i<vMessageContainerNode.childNodes.length;i++)
{var vCurrentMessageAttributesNode=vMessageContainerNode.childNodes.item(i).attributes;var vCurrentMessageScope=getPageMessageAttribute(vCurrentMessageAttributesNode,"scope");if(vCurrentMessageScope=="page")
{var vCurrentMessageType=getPageMessageAttribute(vCurrentMessageAttributesNode,"type");if(vCurrentMessageType=="question")
{return true;}}}
return false;}
function changeMessageButtonToMore()
{createPageMessageButtons(MESSAGEMOREBUTTON,null)}
function processRowScroll(toURL)
{if(parent.getListRecordSet()==null)
return;gProcessEventActionURL=toURL;gProcessEventActionType="scrollrow";var vWindow=getBrowserWindow();vWindow.showStatusMeter();gProcessEventTimer=window.setTimeout("processRowScrollCore()",1);}
function needSaveBeforeScroll()
{var bsave=fieldValuesHasChanges();if(bsave&&!gAutoSave)
bsave=!confirm(MSG_WRN_JS_CHANGES_MADE);return bsave;}
function getScrollReqString()
{var dataset=parent.getListRecordSet();var dataXML=parent.getListRecordXml();var rowElement=dataXML.getElementsByTagName("ROW")[parent.gScrollIndex];var vKeyNames=parent.gScrollKeyNames;vResult="SCROLLROW=YES";for(var i=0;i<vKeyNames.length;i++)
{var vKeyElement=rowElement.getElementsByTagName(vKeyNames[i])[0];if(vKeyElement!=null)
vResult+="&"+vKeyNames[i]+"="+urlEncode(vKeyElement.text);}
return vResult;}
function getListRecordXml()
{var recSet=null;var listframe=document.getElementById("LST_IFRAME");if(isObject(eval("listframe"))&&isObject(eval("listframe.contentWindow"))&&isObject(eval("listframe.contentWindow.griddata")))
{recSet=document.getElementById("LST_IFRAME").contentWindow.griddata;}
return recSet;}
function getListRecordSet()
{var recSet=null;var listXML=getListRecordXml();if(isObject(listXML))
{recSet=listXML.recordset;}
return recSet;}
function processRowScrollCore()
{var namevalues=getScrollReqString();processEventCore("",gProcessEventActionURL,namevalues);getBrowserWindow().hideStatusMeter();gProcessEventActionURL=null;gProcessEventTimer=null;if(!gProcessEventFoundStopError)
{initLastValidatedLOVFieldValue();try
{if(isObject(window.initValues))
{window.initValues();}}
catch(exception)
{}}}
function resetGFocusField(vFocusField)
{if(!isblank(vFocusField))
{gLastFocusField=gFocusField;gFocusField=vFocusField;}}
function confirmStandardMessage(handler)
{if(!getBrowserWindow().messageBarOpen())
{displayQuestionMessageOnPage(0,"YesNoCancel",handler,MSG_STD_JS_CHANGES_MADE,null,window.name);}}
function getInstallParam(paramName)
{var vWindow=getBrowserWindow();var paramValue=null;if(isObject(eval("vWindow."+paramName)))
paramValue=eval("vWindow."+paramName);if(isblank(paramValue))
{if(window.dialogArguments!=null&&dialogArguments.openerwindow!=null&&(isObject(dialogArguments.openerwindow.getInstallParam)))
{paramValue=dialogArguments.openerwindow.getInstallParam(paramName);}}
return paramValue;}
function isAutoSave()
{var autoSave=getInstallParam("INSTALL_AUTOSAVE");var isAutoSaveB=true;if(!isObject(autoSave))
autoSave="YES";if(autoSave.toUpperCase()=="NO")
isAutoSaveB=false;return isAutoSaveB;}
function setMessageBarAutoHideTimeout()
{if(gSuccessMsgTimeout>-1)
{gMessageBarAutoHideTimer=window.setTimeout("messageBarReset()",gSuccessMsgTimeout*1000);}}
function removeMessageBarAutoHideTimeout()
{if(gSuccessMsgTimeout>-1)
{gMessageBarAutoHideTimer=null;}
else
{removeglasstop();}}
function isMessageBarOpen()
{var vReturnValue=false;var vMessageBar=document.getElementById("messagebar");if(isObject(vMessageBar)&&(vMessageBar.style.display=="block"||vMessageBar.style.display==""))
{vReturnValue=true;}
return vReturnValue;}
function killSelect()
{event.cancelBubble=true;return false;}
function killShiftSelect()
{if(event.shiftKey)
return killSelect();}
function setAltText(pObj,pVarName)
{try
{pObj.alt=eval(pVarName);}
catch(exception)
{}}
var gPopupWindowParentPageName=null;function resize_popup(){if(isObject(gNoResizePopup)&&gNoResizePopup)
{return;}
if(isObject(window.dialogArguments))
{if(typeof(window.dialogArguments)=='object')
gPopupWindowParentPageName=window.dialogArguments[0];else if(typeof(window.dialogArguments)=='string')
gPopupWindowParentPageName=window.dialogArguments;}
var pHeight=document.body.offsetHeight+35;var pWidth=document.body.offsetWidth+45;window.dialogWidth=pWidth+'px';window.dialogHeight=pHeight+'px';}
function getDebug()
{var isDebug=false;try
{var bWindow=getBrowserWindow();if(bWindow.opener.document.URL.indexOf('debug')>0)
isDebug=true;}
catch(e){}
return isDebug;}
function clickThisObject()
{if(window.event.keyCode==13)
{window.event.keyCode=0;window.event.cancelBubble=true;window.event.returnValue=false;window.event.srcElement.fireEvent("onclick");return false;}}
function setFieldFocusMed(vFocusField,syncGfieldFocus)
{if(vFocusField=="")
vFocusField=gFirstFocusField;var fieldObj=document.getElementById(vFocusField);if(fieldObj==null)
{try
{var allElements=FIELDVALUES.selectNodes("/FIELDVALUES/ROW/ATTRIBUTES/*/.[string(.)='optional' || string(.)='required']");var i_element=allElements[0];vFocusField=i_element.nodeName;var i_fieldObj=document.getElementById(vFocusField);var i=0;while(i<allElements.length||i==10)
{var ii_element=allElements[i+1];var ii_fieldObj=document.getElementById(ii_element.nodeName);if(i_fieldObj.sourceIndex>ii_fieldObj.sourceIndex)
{vFocusField=ii_element.nodeName;i_element=ii_element;i_fieldObj=document.getElementById(ii_element.nodeName);}
i+=1;}}
catch(e)
{}}
if(document.activeElement==null)
{if(getBrowserWindow().isMessageBarOpen())
{getBrowserWindow().document.getElementById("message_button_cell").focus();}
else
{setFieldFocus(vFocusField,syncGfieldFocus);}}}
function setFieldFocusOnOut()
{if((isObject(gFirstFocusField)||window.gHasDataSpyToolbar)&&lastKeyPressedCode==9)
{try
{killLastKeyPressed();var vDataspylistObj=document.getElementById('dataspylist');if(isObject(window.gHasDataSpyToolbar)&&gHasDataSpyToolbar&&isObject(vDataspylistObj))
setTimeout("setFieldFocusMed('dataspylist')",500);else
setTimeout("setFieldFocusMed('',true)",500);}
catch(e){}}}
function killEvent()
{if(isObject(window.event))
{if(window.event.srcElement!==null&&window.event.srcElement.type!="file")
{event.keyCode=0;}
event.cancelBubble=true;event.returnValue=false;}
return false;}
function checkTabKey()
{if(window.event.keyCode==9&&window.event.srcElement.tagName=='BODY')
{if(isObject("window.checkFieldFocus"))
{try
{checkFieldFocus();}catch(e){}}}}
function setSystemLogout()
{var vWindow=getBrowserWindow();vWindow.closingWindow=true;vWindow.gUserLoggingOut=true;vWindow.returnValue="LOGIN";vWindow.close();}
function callSystemLogout()
{try
{if(isHyperLinkWindow())
{window.close();return} ;}
catch(e)
{}}
function isHyperLinkWindow()
{return isObject(window.dialogArguments)&&typeof(window.dialogArguments[0])=="string"&&window.dialogArguments[0]=='hyperlink'}
function setFieldOnHyplerlinkedWindow()
{if(isHyperLinkWindow())
{disableHyperLinkButtons();var vWin=getNamedIframeWindow("SUBIFRAME");if(vWin)
{if(window.dialogArguments[2]=="I:WEBL"||window.dialogArguments[2]=="R")
{if(isObject(document.getElementById(window.dialogArguments[3])))
{var vFieldAttribute=getFieldAttribute(window.dialogArguments[3]);if(!isblank(vFieldAttribute)&&vFieldAttribute!="hidden"&&vFieldAttribute!="protected")
{if(isblank(getFieldValue(window.dialogArguments[3])))
setFieldValue(window.dialogArguments[3],window.dialogArguments[4]);var v_FieldObj=document.getElementById(window.dialogArguments[3]);if(isObject(document.getElementById(window.dialogArguments[3])+"_lu"))
{v_FieldObj.lastValidatedValue="";v_FieldObj.validated="false";}
gFirstFocusField=window.dialogArguments[3];gFocusField=window.dialogArguments[3];setTimeout("setFieldFocus('"+gFirstFocusField+"')",1000);}}}}}}
function disableHyperLinkButtons()
{var vObj=parent.parent.document.getElementById("DESIGN");if(isObject(vObj))
vObj.style.display="none"
vObj=parent.parent.document.getElementById("FORWARD");if(isObject(vObj))
vObj.style.display="none"
vObj=parent.parent.document.getElementById("BACK");if(isObject(vObj))
vObj.style.display="none"}
function invokeScreen(vHyperlinkSrcField)
{if(window.gPageName=="LOGIN"||window.gPageName=="CLOGON"||window.gPageName=="OSELRS"||window.gPageName=="BSWSPE")
return;var vWindow=getBrowserWindow();if(!(isObject(vWindow.mainWindow)&&vWindow.mainWindow))
return;if(isObject(document.activeElement)&&document.activeElement.id.indexOf("_display")!=-1)
return;if(isObject(window.event))
gWindowSrcElement=window.event.srcElement;else
return;var pHyperLinkSrcField="";if(typeof(vHyperlinkSrcField)=='string')
pHyperLinkSrcField=vHyperlinkSrcField;else
pHyperLinkSrcField="";window.showStatusMeter();window.setTimeout("invokeScreenCore('"+pHyperLinkSrcField+"')",1);}
function invokeScreenCore(pHyperlinkSrcField)
{var vWindow=getBrowserWindow();var vCurrentLevel;var vCurrentWindowWidth=vWindow.document.body.clientWidth;var vCurrentWindowHeight=vWindow.document.body.clientHeight;if(isHyperLinkWindow())
{if(vWindow.dialogArguments[1]==2)
{window.hideStatusMeter();return;}
vCurrentLevel=vWindow.dialogArguments[1]+1;}
else
{vCurrentLevel=1;}
syncFieldValue("pageaction","");syncFieldValue("ONLY_DATA_REQUIRED","true");var userfunction=gUserFunctionName;var pagename=gPageName;var currenttab=gCurrentTab;var parenttab=parent.gCurrentTab;gUserFunctionName="BSHYPL";gPageName="BSHYPL";gCurrentTab=null;parent.gCurrentTab=null;syncFieldValue("sourcepagename",userfunction+"_"+currenttab);var vCurrentElement="";if(isblank(pHyperlinkSrcField))
{if(!isblank(gWindowSrcElement.id))
vCurrentElement=gWindowSrcElement.id;else
{try
{if(!isblank(window.document.activeElement.id))
vCurrentElement=window.document.activeElement.id;else
vCurrentElement=window.document.activeElement.getElementsByTagName("input")[0].id;}
catch(e)
{}}}
else
vCurrentElement=pHyperlinkSrcField;syncFieldValue("sourcefield",vCurrentElement);var vSourceFieldValue=getFieldValue(vCurrentElement);performHyperLinkAction("","GOTO",false,"false");var vDestPageName=getFieldValue("destpagename")
var vSystemFunction=getFieldValue("parentfunction");var vDestField=getFieldValue("destfield");var vFunClass=getFieldValue("funclass");if(!isblank(vDestPageName))
{syncFieldValue("ONLY_DATA_REQUIRED","false");gUserFunctionName=vDestPageName;if(isblank(vSystemFunction))
vSystemFunction=vDestPageName;if(vFunClass=="REPB"||vFunClass=="REPC")
{vSystemFunction="COGREP"
vDestField=getFieldValue("reportparam");}
else if(vFunClass=="WEBR")
{vSystemFunction="EWSREP"
vDestField=getFieldValue("reportparam");}
else if(vFunClass=="WEB")
vSystemFunction="EWSWEB";gPageName=vSystemFunction;var url="../work/loadmain?initpath="+gPageName;url+="&SYSTEM_FUNCTION_NAME="+gPageName;url+="&USER_FUNCTION_NAME="+gUserFunctionName;url+="&MENU_MODULE_KEY=-1";url+="&fromlogin=yes";var vScreenmode=getFieldValue("screenmode");if(vScreenmode=='I')
{url+="&CURRENT_TAB_NAME=HDR";url+="&DEFAULT_VIEW=HDR";url+="&screenmode=I";}
else
url+="&CURRENT_TAB_NAME=LST";if(vFunClass=='WEBL')
url+="&hyperlinksource=F";else
url+="&hyperlinksource=R";var vScreenmodeFunClass=vScreenmode+":"+vFunClass;if(vFunClass=="REPB"||vFunClass=="REPC")
vScreenmodeFunClass="R";var vHeight=roundNumber(multiplyNumber(vCurrentWindowHeight,0.95),0);var style="help:no; status:0; resizable:yes; dialogWidth:"+vCurrentWindowWidth.toString()+"px; dialogHeight:"+vHeight.toString()+"px; center:1";var vArguments=new Array();vArguments=buildHyperLinkArgumentArray(vCurrentLevel,vScreenmodeFunClass,vSourceFieldValue,vDestField);var popWin=dstmShowModalDialog(url,vArguments,style,false);if(!(typeof(popWin)=="undefined"||popWin==null||isblank(popWin)))
{window.hideStatusMeter();if(popWin=="NOACCESS")
{var msgArray=new Array();clearAllFieldMessages();displayStandardMessageOnPage(0,"error",MSG_ERR_NO_ACCESS_PRIVILEGE,msgArray);}
else
{var vFieldAttribute=getFieldAttribute(vCurrentElement);if(!isblank(vFieldAttribute)&&vFieldAttribute!="hidden"&&vFieldAttribute!="protected")
{setFieldValue(vCurrentElement,popWin);}}}
setFieldFocus(vCurrentElement);}
window.hideStatusMeter();gUserFunctionName=userfunction;gPageName=pagename;gCurrentTab=currenttab;parent.gCurrentTab=parenttab;}
function getQuickFilterOperator(){if(isObject(getBrowserWindow().INSTALL_QUICKDEF)){return(getBrowserWindow().INSTALL_QUICKDEF=="C")?"CONTAINS":"BEGINS";}else if(isObject(parent.callerObj)&&isObject(parent.callerObj.addonOpr)){return parent.callerObj.addonOpr;}else if(isObject(window.dialogArguments)&&isObject(window.dialogArguments.QuickFilterOperator)){return window.dialogArguments.QuickFilterOperator;}else{return"BEGINS";}}
function buildHyperLinkArgumentArray(pLevel,pScreenModeFunClass,pSourceFieldValue,pDestField)
{var vArg=new Array();vArg.push("hyperlink");vArg.push(pLevel);vArg.push(pScreenModeFunClass);if(pScreenModeFunClass=="Q:WEBL")
{var vListViewQueryStr="COMPONENT_INFO_TYPE=HEAD_DATA&DATASPY_ID="+urlEncode(getFieldValue("ewsdataspyid"));vListViewQueryStr+="&filterfields="+urlEncode(pDestField);vListViewQueryStr+="&filteroperator="+getQuickFilterOperator()+"&filtervalue="+urlEncode(pSourceFieldValue)+"&";if(!isblank(pSourceFieldValue))
vListViewQueryStr+="ADDONS_REQUIRED=true&";vArg.push(vListViewQueryStr);vArg.push(pDestField);}
else if(pScreenModeFunClass=="I:WEBL"||pScreenModeFunClass=="R")
{vArg.push(pDestField);vArg.push(pSourceFieldValue);}
else
vArg.push(getFieldValue("sourcefield"));return vArg;}
function performHyperLinkAction(pAction,pEvent,pRefreshData,pThrowException)
{var vFunctionResult;setFieldValue("pageaction",pAction);setFieldValue("ONLY_DATA_REQUIRED","true");if(pThrowException=="true")
setFieldValue("THROW_EXCEPTION","true");gBypassUpdateParentWindow=true;vFunctionResult=perform7iFunction("all",false,pEvent,create7iSendHTMLto("window",null,"self"),null,pRefreshData,null);syncFieldValue("pageaction","");syncFieldValue("ONLY_DATA_REQUIRED","");syncFieldValue("THROW_EXCEPTION","");gBypassUpdateParentWindow=false;return true;}
function screenChangesExist()
{var objSub=document.getElementById("MAINIFRAME");if(isObject(eval("objSub.contentWindow.childFrameHasChanges")))
{if(objSub.contentWindow.childFrameHasChanges())
return true;}
return false;}
function ReturnWithValue()
{var vWindow=getBrowserWindow();var vReturn="";if(isObject(parent)&&!screenChangesExist())
{if(isObject(parent.gKeyFields))
{var vSplit=parent.gKeyFields.split(",");var vMainFrameWindow=getMainIframeWindow();if(vMainFrameWindow)
{vReturn=vMainFrameWindow.getFieldValue(vSplit[0]);if(isblank(vReturn))
{if(isObject(vMainFrameWindow.document.getElementById(vSplit[0])))
vReturn=vMainFrameWindow.document.getElementById(vSplit[0]).outerText;else
{if(isblank(vReturn))
{var vSubIFrameWindow=getSubIframeWindow();if(vSubIFrameWindow)
{if(vWindow.dialogArguments[2]=="I:WEBL")
{if(isObject(vSubIFrameWindow.document.getElementById(window.dialogArguments[3])))
vReturn=vSubIFrameWindow.getFieldValue(window.dialogArguments[3]);}
else if(vWindow.dialogArguments[2]=="Q:WEBL")
{if(vSubIFrameWindow.getGridCellValue(window.dialogArguments[4]))
vReturn=vSubIFrameWindow.getGridCellValue(window.dialogArguments[4]);}}}}}}}}
vWindow.returnValue=vReturn;vWindow.closingWindow=true;vWindow.gUserLoggingOut=false;vWindow.close();}
function postToModalDialog(pURL,pNameValues,pStyle)
{var vArray=new Array();vArray.push(pURL);vArray.push(pNameValues);return dstmShowModalDialog('EMPTYPOPUP',vArray,pStyle)}
function getPopupPageContent()
{window.setTimeout("getPopupPageContentCore()",1);}
function getPopupPageContentCore()
{var vArgs=window.dialogArguments;var srcElm=new Object();srcElm.sendhtmlto='contentframe';srcElm.controltype='modal';if(isObject(vArgs))
processEventCore(srcElm,vArgs[0],vArgs[1]);}
function perform7iFunction(pFields,pValidate,pUrl,pSendHtmlTo,pRequestHeaders,pRefreshData,pCustomNameValues)
{var vReturnObject=new Object();vReturnObject.XMLHTTPStatus=null;vReturnObject.XMLHTTPError=true;vReturnObject.responseType="XML";vReturnObject.responseXML=null;vReturnObject.pageError=true;gProcessEventFoundStopError=false;gPerform7iFunctionFoundError=false;gPerform7iFunctionFoundQuestion=false;if(pValidate)
{if(!checkRequiredPage())
{vReturnObject.pageError=true;gPerform7iFunctionFoundError=true;gProcessEventFoundStopError=true;return vReturnObject;}
if(checkAllTextAreaMaxLength()||isFieldMessagePresent())
{displayStandardMessageOnPage(0,"error",MSG_ERR_PAGE_REQUIRED);vReturnObject.pageError=true;gPerform7iFunctionFoundError=true;gProcessEventFoundStopError=true;return vReturnObject;}}
var vProcessEventFieldValueString="";if(pFields!="none")
{vProcessEventFieldValueString=getNameValues(pFields);}
if(pCustomNameValues!=null)
{vProcessEventFieldValueString=pCustomNameValues+"&"+vProcessEventFieldValueString;}
var vSrcObject=new Object();vSrcObject.sendhtmlto="";vSrcObject.controltype="";if(isObject(pSendHtmlTo)&&pSendHtmlTo.type=="iframe")
{vSrcObject.sendhtmlto=pSendHtmlTo.id;vSrcObject.controltype="nonmodal"}
var vProcessEventData=processEventCore(vSrcObject,pUrl,vProcessEventFieldValueString,null,true);if(gPerform7iFunctionFoundError)
{vReturnObject.pageError=true;vReturnObject=makePerform7iReturnXML(vProcessEventData,vReturnObject);return vReturnObject;}
else
{vReturnObject.XMLHTTPStatus=200;vReturnObject.XMLHTTPError=false;vReturnObject=makePerform7iReturnXML(vProcessEventData,vReturnObject);vReturnObject.pageError=false;}
if(pRefreshData)
{copyLastFieldValues();}
return vReturnObject;}
function makePerform7iReturnXML(pProcessEventData,pReturnObject)
{if(pProcessEventData)
{pReturnObject.responseType="XML";var vRoot=new ActiveXObject("Msxml.DOMDocument");vRoot.loadXML(pProcessEventData);pReturnObject.responseXML=vRoot;}
else
{pReturnObject.responseType="redirect";pReturnObject.responseXML=null;}
return pReturnObject;}
function create7iSendHTMLto(pType,pId,pScope)
{if(!isObject(pType)||(pType!="window"&&pType!="iframe")||(!isObject(pId)&&!isObject(pScope))||(pId==""&&pScope==""))
{throw new Error("JavaScript: Invalid call to create7iSendHTMLto()");}
var vNewSendHTMLTo=new Object();vNewSendHTMLTo.type=pType;vNewSendHTMLTo.id=null;vNewSendHTMLTo.scope=null;if(pType=="iframe")
{vNewSendHTMLTo.id=pId;}
else
{vNewSendHTMLTo.scope=pScope;}
return vNewSendHTMLTo;}
function resetControlFieldsLastValidatedValue(vNode)
{var vFld=document.getElementById(vNode.nodeName);if(vFld==null)
return;if(vFld.isControlField=="true")
if(vNode.text)
vFld.lastValidatedValue=vNode.text;else
vFld.lastValidatedValue="";}
function processControlFieldValidate(controlField,fieldChangeFunction)
{var objRef=document.getElementById(controlField);objRef.isControlField="true";var pagemode=getFieldValue("pagemode");if(objRef==null)
return;if(typeof(objRef.validated)=="undefined"||objRef.validated==null)
return;var lastValue=objRef.lastValidatedValue;var currentValue=getFieldValue(controlField);if(lastValue!=""||lastValue!="undefined"||lastValue!=null)
{if(lastValue==currentValue)
return;}
if(currentValue==""||currentValue=="undefined"||currentValue==null)
{setFieldValue(controlField,lastValue);objRef.lastValidatedValue=lastValue;objRef.validated="true";return;}
else
{if(objRef.validated=="false")
return;}
if(pagemode=="display")
{if((lastValue==null||lastValue==""||lastValue=="undefined")&&(currentValue!=null))
{eval(fieldChangeFunction);}
else if(lastValue!=currentValue)
{eval(fieldChangeFunction);}}
objRef.lastValidatedValue=currentValue;}
function addDaysToDate(sfDate,numDays,includeTime)
{if(sfDate==null||sfDate==""||numDays==null)
return;var objDt=new Date(sfDate);var newTime=objDt.getTime()+numDays*24*60*60*1000;newTime=(includeTime)?newTime:newTime+10000000;var objDt2=new Date(newTime);objDt2.month=objDt2.getMonth()+1;objDt2.day=objDt2.getDate();objDt2.year=objDt2.getFullYear();var vDateValue=convertDateToClientFormat(objDt2);if(includeTime){var vTime=validateTime(objDt2.getHours()+":"+objDt2.getMinutes());var failedvalidation=(vTime==false);if(!failedvalidation){return trim(vDateValue+" "+vTime);}}
return trim(vDateValue);}
function requiredFieldBlurCheck()
{var vField=window.event.srcElement;if(isObject(vField)&&isObject(vField.id)&&getFieldAttribute(vField.id).toLowerCase()=="required")
{var ErrorSpan=document.getElementById("fieldErr_"+vField.id);if(!isObject(ErrorSpan))
{return}
if(ErrorSpan.requiredFieldError)
{clearDisplayFieldMessage(vField.id)}}}
function isCustomField(pName)
{if(pName==null)
return false;if(pName.length<6)
return false;return pName.substring(0,5)=="cust_";}
function getSelectedRows()
{var len=gridTable.gSelectedItemsArray.length
var xmlRows=listXML.getElementsByTagName("ROW");var selectedRows=null;for(i=0;i<len;i++)
{if(selectedRows==null)
selectedRows=new Array();selectedRows[i]=xmlRows[parseInt(gridTable.gSelectedItemsArray[i],10)];}
return selectedRows}
function isOldNodeSameAsNewNode(pOrigionalNode,pNewNode)
{if((isObject(gPageName)&&gPageName=="PSPOLN")&&(!isObject(pOrigionalNode)||!isObject(pNewNode)))return true;for(var x=0;x<pOrigionalNode.childNodes.length;x++)
{if(pOrigionalNode.childNodes[x].text!=pNewNode.childNodes[x].text)
{return false;}}
return true;}
var gCurrentFocusField=null;function setGlobalCurrentFocusField()
{gCurrentFocusField=window.event.srcElement;}
function saveChangesOnForm(pForm)
{var saveChanges=true;if(pForm=="SSASGN"||pForm=="SSREQF"||pForm=="SSABCA"||pForm=="SSEOQA")
{saveChanges=false;}
return saveChanges;}
function checkResponseForConfirmMessage(pResultObject)
{var vResult=false;if(isObject(pResultObject)&&isObject(pResultObject.responseXML))
{var vMessageItems=pResultObject.responseXML.getElementsByTagName("MESSAGE_ITEM");for(var x=0;x<vMessageItems.length;x++)
{var vCurrentItem=vMessageItems[x];var vCurrentItemAttributesNode=vCurrentItem.attributes;var vCurrentItemType=vCurrentItemAttributesNode.getNamedItem("type").value;if(vCurrentItemType.toLowerCase()=="question")
{vResult=true;break;}}}
return vResult;}
function checkFieldErrorOnChange()
{var vElem=event.srcElement;var vDataFieldName=vElem.dataFld;if(isObject(vDataFieldName)&&isObject(vElem.lastFieldErrorValue))
{if(vElem.getAttribute("fieldtype")==EG_DATE_FIELD)
{if(vElem.value!=vElem.lastFieldErrorValue&&vElem.validated!="false")
{clearDisplayFieldMessage(vElem.id);}}else
{if(getFieldValue(vDataFieldName)!=vElem.lastFieldErrorValue&&vElem.validated!="false")
{clearDisplayFieldMessage(vElem.id);}}}
else
{vElem.detachEvent("onpropertychange",checkFieldErrorOnChange);}}
function hideShowBlocks()
{var vPageMode=getFieldValue("pagemode").toLowerCase();if((vPageMode=="editlayout"||vPageMode=="previewmode")&&document.getElementById("LAYOUTMETA")!=null)
return;var allFormBorders=document.getElementsByTagName("TABLE");for(i=0;i<allFormBorders.length;i++)
{var tableElement=allFormBorders[i];if(tableElement.className=='formborder'&&!isCustomFieldblock(tableElement))
{var allInputs=tableElement.getElementsByTagName("input");var found=false;for(j=0;j<allInputs.length;j++)
{var inputField=allInputs[j];var ido=inputField.id;if(inputField.style.display!="none"&&inputField.type!="hidden"&&isObject(ido)&&ido!="")
{ido=ido.replace("_display","");var attr=getFieldAttribute(ido);if(attr!="hidden")
{found=true;break;}}}
if(!found)
{var allTexts=tableElement.getElementsByTagName("TEXTAREA");for(j=0;j<allTexts.length;j++)
{var inputField=allTexts[j];var ido=inputField.id;if(inputField.style.display!="none"&&isObject(ido)&&ido!="")
{ido=ido.replace("_display","");var attr=getFieldAttribute(ido);if(attr!="hidden")
{found=true;break;}}}}
if(!found)
{var allTexts=tableElement.getElementsByTagName("span");for(j=0;j<allTexts.length;j++)
{var inputField=allTexts[j];var ido=inputField.id;if(inputField.datatype=="link"&&inputField.style.display!="none"&&isObject(ido)&&ido!="")
{var attr=getFieldAttribute(ido);if(attr!="hidden")
{found=true;break;}}}}
if(!found)
{var allTexts=tableElement.getElementsByTagName("table");for(j=0;j<allTexts.length;j++)
{var inputField=allTexts[j];var ido=inputField.id;if(inputField.datatype=="button"&&inputField.style.display!="none"&&isObject(ido)&&ido!="")
{var attr=getFieldAttribute(ido);if(attr!="hidden")
{found=true;break;}}}}
if(!found)
{var allTexts=tableElement.getElementsByTagName("iframe");for(j=0;j<allTexts.length;j++)
{var inputField=allTexts[j];if(inputField.style.display!='none')
{found=true;break;}}}
var spanObj=null;var spans=tableElement.getElementsByTagName("td");for(k=0;k<spans.length;k++)
{var tempSpanObj=spans[k];if(tempSpanObj.className=='formlabel')
{spanObj=tempSpanObj;break;}}
if(!found)
{if(spanObj!=null)
spanObj.style.display="none";}
else if(spanObj!=null)
{spanObj.style.display="block";}}}}
function isCustomFieldblock(tableElement)
{var allTables=tableElement.getElementsByTagName("table");for(m=0;m<allTables.length;m++)
{var tableObj=allTables[m];if(isObject(tableObj.id)&&tableObj.id.indexOf("custom_")!=-1)
{return true;}}
return false;}
function CopyRecordStatus(fromList)
{this.copyRecordFlag=true;this.isCopyRecordFromList=fromList;}
function getCopyRecordStatus()
{var mainFrameWindow=getMainIframeWindow();if(mainFrameWindow!=null)
return mainFrameWindow.gCopyRecordStatus;return null;}
function copyRecord()
{var mainFrameWindow=getMainIframeWindow();if(isObject(eval("mainFrameWindow.gCurrentTab")))
{if(mainFrameWindow.gCurrentTab=="HDR")
mainFrameWindow.gCopyRecordStatus=new CopyRecordStatus(false);else
{if(checkKeyFields())
return;mainFrameWindow.gCopyRecordStatus=new CopyRecordStatus(true);}
clickToolbarIcon("NEW",'normalicons');}}
function isCopyRecordOn()
{var copyRecordStatus=getCopyRecordStatus();var copyRecordOn=false;if(copyRecordStatus!=null)
{copyRecordOn=copyRecordStatus.copyRecordFlag;}
return copyRecordOn;}
function resetCopyRecord()
{var mainFrameWindow=getMainIframeWindow();if(mainFrameWindow!=null&&mainFrameWindow.gCopyRecordStatus!=null)
{mainFrameWindow.gCopyRecordStatus=null;}
syncFieldValue("pageaction","");}
function getOrgFieldValue(fieldName)
{try
{var mainFrameWindow=getMainIframeWindow();if(mainFrameWindow.gCurrentTab=="HDR")
return getFieldValue(fieldName);else if(mainFrameWindow.gCurrentTab=="LST")
return getFieldValue(fieldName);else
{return mainFrameWindow.getFieldValue(fieldName);}}
catch(e)
{return getFieldValue(fieldName);}}
function getOrgServerTime(fieldText)
{if(!isObject(fieldText))
fieldText="";var orgField=null;var orgValue="";if(isObject(eval("window.gOrganization")))
orgField=window.gOrganization;try
{if(!isObject(orgField)||orgField=="")
orgField=getMainIframeWindow().gOrganization;}
catch(e){}
if(orgField!=null&&orgField!="")
{orgValue=getOrgFieldValue(orgField);if(orgValue==null||orgValue=="")
orgValue=getOrgFieldValue(orgField.toLowerCase());if(orgValue==null||orgValue=="")
orgValue=getOrgFieldValue(orgField.toUpperCase());}
var responseObject=formSubmitXMLHTTP("DATETIME","dateValue="+fieldText+"&organization="+orgValue);if(typeof(responseObject)!='string')
{var vRoot=new ActiveXObject("Msxml.DOMDocument");vRoot.loadXML(responseObject[1]);return vRoot.documentElement.getElementsByTagName("value")[0].text}
return"";}
function checkError()
{try
{if(isFieldMessagePresent())
{return false;}
vWindow=getBrowserWindow();if(vWindow.gErrorInMessages||gErrorInMessages)
return false;}
catch(e){}
return true;}
function checkProcessEventCallFn()
{showBrowserStatusMeter();window.setTimeout("hideBrowserStatusMeter()",2000);}
function hideBrowserStatusMeter()
{var statusMeter=getBrowserWindow().document.getElementById("statusmeter");if(isObject(statusMeter))
statusMeter.style.display="none";}
function showBrowserStatusMeter()
{var statusMeter=getBrowserWindow().document.getElementById("statusmeter");if(isObject(statusMeter))
statusMeter.style.display="block";}
function killLastKeyPressed()
{var vWindow=getBrowserWindow();vWindow.lastKeyPressedCode=-1;var vIframeRef=vWindow.document.all("MAINIFRAME");if(isObject(vIframeRef)&&isObject(vIframeRef.contentWindow)&&isObject(vIframeRef.contentWindow.lastKeyPressedCode))
{vIframeRef.contentWindow.lastKeyPressedCode=-1;var vSubIframeRef=vIframeRef.contentWindow.document.all("SUBIFRAME");if(isObject(vSubIframeRef)&&isObject(vSubIframeRef.contentWindow))
{try
{vSubIframeRef.contentWindow.lastKeyPressedCode=-1;}
catch(exception)
{}}}}
function EventTraceObject(pEvent)
{this.event=pEvent;this.timeFired=new Date();}
function registerMouseEventSave()
{if(!isObject(window.event))
return;var obj=document.all('SAVE');var eventArry=obj.eventArray;if(eventArry==null)
{eventArry=new Array();obj.eventArray=eventArry;}
obj.eventArray.push(new EventTraceObject(window.event.type));if(window.event.type=="mousedown")
{window.setTimeout("checkIfClickFired('"+obj.id+"')",1000);}}
function registerMouseEventSubmit()
{if(!isObject(window.event))
return;var obj=document.all('submit');var eventArry=obj.eventArray;if(eventArry==null)
{eventArry=new Array();obj.eventArray=eventArry;}
obj.eventArray.push(new EventTraceObject(window.event.type));if(window.event.type=="mousedown")
{window.setTimeout("checkIfClickFired('"+obj.id+"')",1000);}}
function registerMouseEventClear()
{if(!isObject(window.event))
return;var obj=document.all('clear');var eventArry=obj.eventArray;if(eventArry==null)
{eventArry=new Array();obj.eventArray=eventArry;}
obj.eventArray.push(new EventTraceObject(window.event.type));if(window.event.type=="mousedown")
{window.setTimeout("checkIfClickFired('"+obj.id+"')",1000);}}
function checkIfClickFired(pButtonName)
{var obj=document.all(pButtonName)
if(isObject(obj))
{if(obj.eventArray!=null)
{var mouseDownEventObject=obj.eventArray[0];var clickEventObject=obj.eventArray[1];if(mouseDownEventObject!=null&&mouseDownEventObject.event=='mousedown'&&clickEventObject==null&&isObject(getBrowserWindow().gLastGlasstopOn))
{if(getBrowserWindow().gLastGlasstopOn.getTime()-mouseDownEventObject.timeFired.getTime()<2000)
{if(pButtonName=='SAVE')
completeClickToolbarIcon(pButtonName,'normalicons');else if(pButtonName=='submit')
completeSubmitClick();else if(pButtonName=='clear')
{var vButton=document.getElementById('clear');if(isObject(vButton)&&getFieldAttribute('clear')=='optional')
{vButton.fireEvent("onclick");}}}}}
obj.eventArray=null;}}
function completeSubmitClick()
{if(isObject(gProcessEventTimer))
{gSaveSubmitTimer=window.setTimeout("completeSubmitClick()",500);return;}
var vWindow=getBrowserWindow();var mainFrame=vWindow.document.getElementById("MAINIFRAME");if(isObject(mainFrame)&&isObject(mainFrame.gProcessEventTimer))
{gSaveSubmitTimer=window.setTimeout("completeSubmitClick()",500);return}
if(isObject(mainFrame))
{var subFrame=getSubIframeWindow()
if(isObject(subFrame)&&isObject(subFrame.gProcessEventTimer))
{gSaveSubmitTimer=window.setTimeout("completeSubmitClick()",500);return;}}
var vMessageBar=getBrowserWindow().document.getElementById("messagebar");if(typeof(vMessageBar)!="undefined"&&vMessageBar!=null)
{if(vMessageBar.style.display=="block")
{if(gQuestionInMessages||getBrowserWindow().gQuestionInMessages)
{gSaveSubmitTimer=window.setTimeout("completeSubmitClick()",500);return;}
if(gErrorInMessages||getBrowserWindow().gErrorInMessages)
return;}}
var vButton=document.getElementById('submit');if(isObject(vButton)&&getFieldAttribute('submit')=='optional')
{vButton.fireEvent("onclick");}}
function showValue()
{if(isObject(window.event.srcElement)&&window.event.srcElement.disabled)
{globalLookupHelpObj=window.event.srcElement;globalLookupHelpTimer=window.setTimeout("showValueTimer()",1000);killEvent();}}
function showElementDescription(descriptionElement,elementToDescribe){var descDiv=document.getElementById("descdiv");if(!isObject(descDiv)){return;}
if(!hasVerticalScrollBar()){hideVerticalScrollBar();}
descDiv.style.display="block";setElementDescription(descriptionElement,elementToDescribe.value);}
function setElementDescription(descriptionElement,descriptionValue){var textDisplay=descriptionValue;if((!isblank(textDisplay))&&textDisplay.length>40){textDisplay="<table class='form' width='300'><tr><td>"+textDisplay+"</td></tr></table>";descriptionElement.align='left';}else{textDisplay="<table class='form'><tr><td>"+textDisplay+"</td></tr></table>";descriptionElement.align='center';}
descriptionElement.innerHTML=textDisplay;}
function showValueTimer()
{var descDiv=document.getElementById("descdiv");if(isObject(descDiv)&&isObject(globalLookupHelpObj)&&isObject(globalLookupHelpObj.valueFieldID))
{var valueField=document.getElementById(globalLookupHelpObj.valueFieldID);if(valueField.value!="")
{showElementDescription(descdiv_desc,valueField);setElementPosition(descDiv,valueField,3,3,"center","middle","left","down")}}}
function hidedesc()
{showVerticalScrollBar();globalLookupHelpObj=null;window.clearTimeout(globalLookupHelpTimer);globalLookupHelpTimer=null;var descDiv=document.getElementById("descdiv");if(descDiv!=null)
{descDiv.style.display="none";descdiv_code.innerText="";descdiv_desc.innerText="";}}
function getReportQueryString()
{var bWindow=getBrowserWindow();var tReportQueryString=bWindow.gReportQueryString;bWindow.gReportQueryString=null;return tReportQueryString;}
function runSingleRecordReportActual(pPrintType,pNameValues,pChkbox_waitreport,pReportName,pReportClass)
{if(!isObject(pChkbox_waitreport))
pChkbox_waitreport="-1";win=getBrowserWindow();win.hideStatusMeter();getBrowserWindow().gReportQueryString=arguments;var advMode=false;var requestMode;if(isObject(pReportName))
{if((pReportClass=="REPB")||(pReportClass=="REPC"))
{requestMode="record";advMode=true;}}
else if((gPageName=="COGREP")||(gPageName=="COGREPC")||(gPageName=="COGQREPC"))
{requestMode="generic";advMode=true;pReportName=gUserFunctionName;}
else if(isObject(parent.gReportClass)&&(parent.gReportClass=="REPB"||parent.gReportClass=="REPC"))
{requestMode="record";advMode=true;pReportName=parent.gReportName;}
if(advMode==true)
asyncRunReport(pPrintType,pNameValues,requestMode,pReportName);else if(isObject(parent.gReportClass)&&parent.gReportClass=="REP")
{var serverReportDateTime=new Date(gReportDateTime);var timeDiff=serverReportDateTime.valueOf()-gClientReportDateTime.valueOf();var reportMilliseconds=new Date().valueOf();reportMilliseconds=reportMilliseconds+timeDiff;var reportDate=new Date(reportMilliseconds);var url=getInstallParam("INSTALL_REPSURL");url=url+"?"+urlEncode(getInstallParam("INSTALL_EWSRKEY"))+"+"+urlEncode(getInstallParam("INSTALL_REPSERV"))+"+"+urlEncode(parent.gReportFile.toLowerCase()+".rep")+"+";url=url+"R5_USER="+urlEncode(getInstallParam("INSTALL_USER"))+"+";url=url+"R5_ORG="+urlEncode(getInstallParam("INSTALL_USERORG"))+"+";url=url+"R5_LANG="+getInstallParam("INSTALL_LANGUAGE")+"+";url=url+"R5_FUNC="+parent.gReportName+"+";url=url+"R5_VERSION="+urlEncode(getInstallParam("INSTALL_VERSION"))+"+";url=url+"DATEFORMAT="+urlEncode(getInstallParam("INSTALL_FORMDATE"))+"+";url=url+"DATIFORMAT="+urlEncode(getInstallParam("INSTALL_FORMDATI"))+"+";var minutes=reportDate.getMinutes().toString();if(minutes.length<2)
minutes="0"+minutes;url=url+"CURDATETIME="+(reportDate.getMonth()+1)+"/"+reportDate.getDate()+"/"+reportDate.getFullYear()+escape(" ")+reportDate.getHours()+":"+minutes+"+";var paramArray=parent.gReportParameters.split(",");var nameValuePair;var paramValue;for(i=0;i<paramArray.length;i++)
{nameValuePair=paramArray[i].split("=");paramValue=getFieldValue(nameValuePair[1].toUpperCase());if(paramValue==""||paramValue==null)
{paramValue=getFieldValue(nameValuePair[1].toLowerCase());}
url=url+nameValuePair[0]+"="+urlEncode(paramValue);if(i<paramArray.length-1)
url=url+"+";}
window.open(url);}
else if(isObject(pChkbox_waitreport)&&(pChkbox_waitreport=="-1"))
window.open("EmptyReport");else
asyncRunReport(pPrintType,pNameValues);}
function asyncRunReport(pPrintType,pNameValues,pCognosRequestMode,pReportName)
{try
{var vResponse
if(isObject(pCognosRequestMode))
{pNameValues=pNameValues+"&SYSTEM_FUNCTION_NAME=COGWEB";pNameValues=pNameValues+"&USER_FUNCTION_NAME="+pReportName+"&REQUEST_MODE="+pCognosRequestMode;vResponse=formSubmitXMLHTTP("COGWEB",pNameValues);if(vResponse[0]!==null)
{if(vResponse[0].indexOf("text/html")>-1)
loadProcessEventReturnHTML(vResponse[1],"","","");else
loadProcessEventReturnXML(vResponse[1],null,null);}
if('WSJOBS'==window.gPageName&&'HDR'==window.gCurrentTab){var vSubIFrame=window.document.getElementById("SUBIFRAME");vSubIFrame.contentWindow.performAction("",false,"WSJOBS.HDR","",true,"");}
return;}
else
vResponse=formSubmitXMLHTTP("BSREPT.RPT",pNameValues);if(vResponse=="false")
{displayStandardMessageOnPage("","error",MSG_ERR_XMLHTTP_SERVER_COMMUNICATION);}
else
{if((vResponse[0]!=null)&&(vResponse[0].indexOf("text/xml")>-1))
{loadProcessEventReturnXML(vResponse[1],false,false);}}}
catch(e){}
finally
{win=getBrowserWindow();win.hideStatusMeter();}}
function runSingleRecordReportInWindow(pType,pNameValues)
{try
{var vResponse=formSubmitXMLHTTP("BSREPT.RPT",pNameValues);if(vResponse=="false")
{displayStandardMessageOnPage("","error",MSG_ERR_XMLHTTP_SERVER_COMMUNICATION);}
else
{if((vResponse[0]!=null)&&(vResponse[0].indexOf("text/xml")>-1))
{var vRoot=new ActiveXObject("Msxml.DOMDocument");vRoot.loadXML(vResponse[1]);var fileTicket=vRoot.getElementsByTagName("reporturl")[0];if(fileTicket!=null&&fileTicket.text!="")
{var emptyForm=document.forms[0]
emptyForm.filename.value=fileTicket.text;emptyForm.command.value="get";emptyForm.fext.value="pdf";emptyForm.type.value="file.pdf";emptyForm.submit.click();}
else
{loadProcessEventReturnXML(vResponse[1],false,false);}}
else if((vResponse[0]!=null)&&(vResponse[0].indexOf("text/html")>-1))
{loadProcessEventReturnHTML(vResponse[1],"","","");}
if('WSJOBS'==window.opener.gPageName&&'HDR'==window.opener.gCurrentTab){var vSubIFrame=window.opener.document.getElementById("SUBIFRAME");vSubIFrame.contentWindow.performAction("",false,"WSJOBS.HDR","",true,"");}}}
catch(e){}
finally
{win=getBrowserWindow();win.hideStatusMeter();}}
function runSingleRecordReportWithMessage(pPrintType,pNameValues,pChkbox_waitreport,pReportName,pReportClass)
{getBrowserWindow().hideStatusMeter();runSingleRecordReportActual(pPrintType,pNameValues,pChkbox_waitreport,pReportName,pReportClass);}
function noCancelWrapper(pFunctionCall)
{gQuestionInMessages=false;getBrowserWindow().gQuestionInMessages=false;if(isObject(getBrowserWindow().gSaveSubmitTimer))
getBrowserWindow().clearTimeout(getBrowserWindow().gSaveSubmitTimer)
if(isObject(gSaveSubmitTimer))
window.clearTimeout(gSaveSubmitTimer)
eval(pFunctionCall);}
function messageBarButtonOnkeydown()
{if(window.event.keyCode==13||window.event.keyCode==32)
{var vbutton=window.document.getElementById(event.srcElement.id);if(vbutton!=null&&isObject(vbutton.children(0))&&isObject(vbutton.children(0).rows[0]))
{window.event.srcElement.detachEvent("onkeydown",messageBarButtonOnkeydown);window.setTimeout("window.document.getElementById('"+window.event.srcElement.id+"').children(0).rows[0].click()",15);}
window.event.cancelBubble=true;return false;}
if(window.event.keyCode==9)
{if(window.event.srcElement.lastButton)
{if(window.event.srcElement.id=="message_button_cell")
{messageBarFocusSet(null,true);}
else
{document.getElementById("message_button_cell").focus();window.event.cancelBubble=true;return false;}}}}
function isTabInAddTabList(pTabName,pWindowObject)
{if(!isObject(pWindowObject))
{pWindowObject=getMainIframeWindow();}
var vTabsSource=pWindowObject.document.getElementById("tabs_list").XMLDocument;var vTabsSourceContainer=vTabsSource.getElementsByTagName("TABS")[0];var vTabFound=false;for(var i=0;i<vTabsSourceContainer.childNodes.length;i++)
{var vCurrentListNode=vTabsSourceContainer.childNodes.item(i);var vCurrentTabName=vCurrentListNode.getElementsByTagName("TABNAME")[0].text;if(vCurrentTabName==pTabName)
{vTabFound=true;break;}}
return vTabFound;}
function preLoadMessageBar()
{if(getBrowserWindow().isMessageBarOpen())
return;getBrowserWindow().gPreLoadMessageOnly=true;displayStandardMessageOnPage(0,"confirm","Success!");setTimeout("closeDummyMessages()",1);}
function closeDummyMessages()
{var vWindow=window;if(!isObject(window.frameElement)||!isObject(window.frameElement.localMessages)||window.frameElement.localMessages!="true")
{vWindow=getBrowserWindow();}
vWindow.messageBarReset();}
function showTabInAddTabList(pTabName,pWindowObject)
{if(!isObject(pWindowObject))
{pWindowObject=getMainIframeWindow();}
pWindowObject.moveTabs("tabs_list","tabs_display",pTabName);var vTabsDisplay=pWindowObject.document.getElementById("tabs_display").XMLDocument;var vTabsDisplayContainer=vTabsDisplay.getElementsByTagName("TABS")[0];vTabsDisplayContainer.attributes.item(0).text=pTabName;pWindowObject.initTabs();}
function replaceStrs(stringValue)
{stringValue=stringValue.replace(/(\()|(\))|(\')/g, "\\"+"$&");

stringValue=stringValue.replace(/\&gt;/g,">");stringValue=stringValue.replace(/\&lt;/g,"<");stringValue=stringValue.replace(/\&amp;/g,"&");stringValue=stringValue.replace(/\&quot;/g,'"');stringValue=stringValue.replace(/\&apos;/g,".");return stringValue;}
function isURL(pFilePath)
{var urlRegExp=new RegExp("^((http|ftp|https):\/\/)","i");return urlRegExp.test(pFilePath);}
function checkHeadrOnlyRecords()
{if(isObject(window.lookupdata))
return;try
{var metadataXML=window.document.getElementById("metadata");var reqTyp="HEAD_DATA";if(metadataXML!=null&&metadataXML.getElementsByTagName("REQUEST_TYPE_META")[0]!=null)
reqTyp=metadataXML.getElementsByTagName("REQUEST_TYPE_META")[0].text;var mainFrameWindow=getMainIframeWindow();if(reqTyp.indexOf("HEAD_ONLY")!=-1&&isObject(document.getElementById("runBtn"))&&(mainFrameWindow.gCurrentTab=="LST"||mainFrameWindow.gCurrentTab=="STC"||(mainFrameWindow.gCurrentTab=="HDR"&&(window.gIsStandAloneScreen||mainFrameWindow.gPageName=="SSHCST"||mainFrameWindow.gPageName=="BSEXIM")||(mainFrameWindow.gPageName=="BSEXIM"&&mainFrameWindow.gCurrentTab=="CUS")||(mainFrameWindow.gPageName=="BSEXIM"&&mainFrameWindow.gCurrentTab=="INB")||(mainFrameWindow.gPageName=="BSEXIM"&&mainFrameWindow.gCurrentTab=="FLX"))))
{displayClickRunToExecuteMsg();}
else
{clearClickRunToExecuteMsg();}}
catch(e){}}
function displayMsgOnListView(msg){try{AutoRunRecordLabel.innerText=msg;AutoRunRecordTable.style.display="block";if(gridhead_table.offsetWidth>0){AutoRunRecordTable.style.width=gridhead_table.offsetWidth;}
gridbody_table.style.display="none";disableOKButton(true);}catch(e){}}
function displayClickRunToExecuteMsg(){displayMsgOnListView(MESSAGE_RUN_DATASPY);}
function clearMsgOnListView(){try{AutoRunRecordTable.style.display="none";gridbody_table.style.display="block";disableOKButton(false);}catch(e){}}
function clearClickRunToExecuteMsg(){clearMsgOnListView();}
function spelling_check(textObj)
{if(window.event.keyCode!=118)
return true;try
{var Word,Doc,Uncorrected,Corrected;var wdDoNotSaveChanges=0;Uncorrected=textObj.value;Word=new ActiveXObject("Word.Application");Word.Visible=false;Doc=Word.Documents.Add();Word.Selection.Text=Uncorrected;Word.Dialogs(828).Show();if(Word.Selection.Text.length!=1)
Corrected=Word.Selection.Text;else
Corrected=Uncorrected;textObj.value=Corrected;Doc.Close(wdDoNotSaveChanges);Doc=null;Word.Quit();Word=null;killEvent();}
catch(e)
{pHelpURL='../help/'+gUserLanguage+'/formhelp/spellc.htm';displayStandardMessageOnPage(0,"info",MSG_INFO_HOW_TO_ENABLE_SPELLCHECK.replace(":param1",pHelpURL+"' target='_blank"));}}
function getRandomNumber(x,y){var range=y-x+1;return Math.floor(Math.random()*range)+x;}
function loadCognosReport(pRoot){var cUrl="",cPassport="",cCRN="",cRedirectUrl="",cTenantID="";cUrl=pRoot.getElementsByTagName("weburl")[0].text;cPassport=pRoot.getElementsByTagName("passport")[0].text;cCRN=pRoot.getElementsByTagName("crn")[0].text;cRedirectUrl=pRoot.getElementsByTagName("redirecturl")[0].text;cTenantID=pRoot.getElementsByTagName("tenantid")[0].text;var wind=open("","_blank","resizable=yes,left=10,top=10,status=yes");wind.resizeTo(document.body.clientWidth,document.body.clientHeight);wind.document.open();wind.document.write("<html>");wind.document.write("<head>");wind.document.write("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");wind.document.write("</head>");wind.document.write("<body>");wind.document.write("<form name='fm1' method='post' action='");wind.document.write(cUrl);wind.document.write("'>");wind.document.write("<input type='hidden' name='cam_passport' value='");wind.document.write(cPassport);wind.document.write("'/>");wind.document.write("<input type='hidden' name='CRN' value='");wind.document.write(cCRN);wind.document.write("'/>");wind.document.write("<input type='hidden' name='tenant' value='");wind.document.write(cTenantID);wind.document.write("'/>");wind.document.write("<input type='hidden' name='url' value='");wind.document.write(cRedirectUrl);wind.document.write("'/>");wind.document.write("</body></html>");wind.document.forms("fm1").submit();}
function isCachedScreen(func)
{if(gPageMode=="editlayout"||!isObject(window.gScreenCacheList))
return false;if(!isObject(func))
func=gUserFunctionName;if(func=="")
return false;return gScreenCacheList.indexOf(func)>-1;}
function getSysUserFuncNames(action)
{var vReturn=new Array();if(isObject(action))
{var lInd=action.lastIndexOf("/");tAction=action.substring(lInd+1,action.length);var functions=tAction.split("?");vReturn[0]=functions[0];if(action.indexOf("USER_FUNCTION")!=-1)
vReturn[1]=functions[1].split("=")[1];else
vReturn[1]=functions[0];}
return vReturn;}
function makeMainIframe(fname)
{var bCached=isCachedScreen(fname);var vCurrentMainframe=document.getElementById("MAINIFRAME");if(!bCached)
fname="_ALL";if(vCurrentMainframe!=null)
{vCurrentMainframe.id=vCurrentMainframe.oldid;vCurrentMainframe.name=vCurrentMainframe.oldname;}
gLastMainIframe=gActiveMainIframe;gActiveMainIframe=document.getElementById(fname+"_MAINIFRAME");var vIframeContainer=document.getElementById("mainiframecontainer");if(!isObject(gActiveMainIframe))
{var vTemplateIfame=document.getElementById("CLONEMAINIFRAME");var vNewIframe=vTemplateIfame.cloneNode(true);vNewIframe.id=fname+"_MAINIFRAME";vNewIframe.name=fname+"_MAINIFRAME";vNewIframe.oldid=fname+"_MAINIFRAME";vNewIframe.oldname=fname+"_MAINIFRAME";vNewIframe.cached=bCached;vIframeContainer.appendChild(vNewIframe);gActiveMainIframe=vNewIframe;}
gActiveMainIframe.id="MAINIFRAME";gActiveMainIframe.name="MAINIFRAME";if(gLastMainIframe&&gActiveMainIframe.oldid!=gLastMainIframe.oldid)
{gLastMainIframe.id=gLastMainIframe.oldid;gLastMainIframe.name=gLastMainIframe.oldname;}
return gActiveMainIframe;}
function showHideMainIframes(bCheckLST)
{var vIframeContainer=document.getElementById("mainiframecontainer");for(var r=0;r<vIframeContainer.childNodes.length;r++)
{var vframe=vIframeContainer.childNodes[r];if(vframe.removed&&vframe.oldid!=gActiveMainIframe.oldid)
{if(isObject(vframe.contentWindow.rememberValues))
{vframe.contentWindow.rememberValues();vframe.contentWindow.document.body.onunload=null;}
vIframeContainer.removeChild(vframe)}
else
vframe.style.display="none";}
gActiveMainIframe.style.display="block";if(bCheckLST)
{if(isObject(gActiveMainIframe.contentWindow.initValues))
gActiveMainIframe.contentWindow.initValues();var subframe=gActiveMainIframe.contentWindow.gActiveSubIFrame;if(subframe==null)
return;if(subframe.id=="LST_IFRAME")
{gActiveMainIframe.contentWindow.refreshListViewTabDataOnly();if(subframe.contentWindow.setFocusOnTextField)
subframe.contentWindow.setFocusOnTextField();}
else if(subframe.refreshtabdata)
{gActiveMainIframe.contentWindow.getSubIframeContents(false,true);subframe.refreshdata=false;}
else
subframe.contentWindow.initCachedScreen();}}
function restoreLastMainIframe()
{gActiveMainIframe.id=gActiveMainIframe.oldid;gActiveMainIframe.name=gActiveMainIframe.oldname;gActiveMainIframe=gLastMainIframe;gActiveMainIframe.id="MAINIFRAME";gActiveMainIframe.name="MAINIFRAME";gPageHistory.pop();}
function initCachedScreen()
{try
{if(isObject(window.changeParentDisplayFields))
changeParentDisplayFields();if(isObject(parent.checkScrollButtonStatus))
parent.checkScrollButtonStatus();if(gFocusField)
setFieldFocus(gFocusField,false);}
catch(e)
{}}
function addToScreenCache(fname)
{if(gScreenCacheList==null)
gScreenCacheList=fname+",";else if(gScreenCacheList.indexOf(fname)<0)
{gScreenCacheList=gScreenCacheList+fname+",";}
cacheCurrentMainIframe(fname);}
function removeFromScreenCache(fname)
{if(isObject(gScreenCacheList))
{gScreenCacheList=gScreenCacheList.replace(fname+",","");}
if(isblank(gActiveMainIframe.contentWindow.gScreenFlwoID))
gActiveMainIframe.removed=true;if(isObject(gActiveMainIframe.contentWindow.removeCachedTabs))
gActiveMainIframe.contentWindow.removeCachedTabs();}
function cacheCurrentMainIframe(fname)
{if(isblank(gActiveMainIframe.contentWindow.gScreenFlwoID))
{gActiveMainIframe.oldid=fname+"_MAINIFRAME";gActiveMainIframe.oldname=fname+"_MAINIFRAME";gActiveMainIframe.cached=true;}
if(isObject(gActiveMainIframe.contentWindow.cacheCurrentTab))
gActiveMainIframe.contentWindow.cacheCurrentTab();}
function useCachedScreen(obj)
{if(obj.cached)
return obj.src.indexOf("COMMON")>-1||obj.src.indexOf("/htmls/")>-1;return false;}
function afterExitScreenDesigner(fname,pPageName)
{var obj=makeMainIframe(fname);MM_goToSubframeWindow("MAINIFRAME",pPageName+"?USER_FUNCTION_NAME="+urlEncode(fname));}
function bodyOnUnload()
{try
{if(oCtxMenu)
{oCtxMenu.Destroy();oCtxMenu=null;}
var saveBtn=getBrowserWindow().document.getElementById("SAVE");if(saveBtn)
saveBtn.detachEvent("onmousedown",checkProcessEventCallFn);if(isObject(window.cleanUp))
cleanUp();}
catch(e)
{}}
function showhide_block(srcElem)
{if(!isObject(srcElem))
srcElem=window.event.srcElement
var imgid=srcElem.id;var pos=imgid.lastIndexOf("_");var bname=imgid.substr(0,pos);if(srcElem.className=="block_expand")
{srcElem.className="block_collapse";document.getElementById(bname+"_expander").style.display="block";showHideRelatedBlocks(srcElem,true)}
else
{srcElem.className="block_expand";document.getElementById(bname+"_expander").style.display="none";showHideRelatedBlocks(srcElem,false)}}
function showHideRelatedBlocks(vObj,bShow)
{if(!isObject(vObj)||!isObject(vObj.linkedblocks))
return;var vBlocks=vObj.linkedblocks.split(",");var vClass=bShow?"block_collapse":"block_expand";var vdisplay=bShow?"block":"none";for(var i=0;i<vBlocks.length;i++)
{document.getElementById(vBlocks[i]+"_control").className=vClass;document.getElementById(vBlocks[i]+"_expander").style.display=vdisplay;}}
function processEmail(vfield)
{location.href="mailto:"+getFieldValue(vfield);}
function attachJSEvent(objid,pEvent,phandler)
{var vObj=document.getElementById(objid);if(vObj)
vObj.attachEvent(pEvent,phandler);}
function getKeyFieldValuesForForward()
{var vQueryString=""
if(isObject(gKeyFields)&&!isblank(gKeyFields))
{var vKeyFieldArray=gKeyFields.split(",");if(vKeyFieldArray.length>0)
{for(var b=0;b<vKeyFieldArray.length;b++)
{var vKeyFieldValue=getFieldValue(vKeyFieldArray[b],gCurrentFieldValues);if(!isObject(vKeyFieldValue)||isblank(trim(vKeyFieldValue)))
{vKeyFieldValue=getFieldValue(vKeyFieldArray[b].toLowerCase(),gCurrentFieldValues);}
if(!isObject(vKeyFieldValue)||isblank(trim(vKeyFieldValue)))
{vKeyFieldValue=getFieldValue(vKeyFieldArray[b].toUpperCase(),gCurrentFieldValues);}
vQueryString+=vKeyFieldArray[b].toLowerCase()+"="+urlEncode(vKeyFieldValue)+"&";}
vQueryString=vQueryString.substr(0,vQueryString.length-1);}}
return vQueryString;}
function initLastValidatedLOVFieldValue()
{var vElements=document.getElementsByTagName('input');for(i=0;i<vElements.length;i++)
{if(isObject(vElements[i].onvalidate)&&vElements[i].onvalidate=='true')
{vElements[i].lastValidatedValue=vElements[i].value;}}}
function showValueTimerForCalCell()
{var descDiv=document.getElementById("descdiv");if(isObject(descDiv)&&isObject(gObejectForCalCell))
{var vTempCalCellValue=gObejectForCalCell.description;if(!isblank(vTempCalCellValue))
{showElementDescriptionForCalCell(descdiv_desc,vTempCalCellValue);setElementPosition(descDiv,gObejectForCalCell,3,3,"center","middle","left","down")}}}
function showElementDescriptionForCalCell(descriptionElement,elementToDescribe)
{var descDiv=document.getElementById("descdiv");if(!isObject(descDiv))
{return;}
if(!hasVerticalScrollBar())
{hideVerticalScrollBar();}
descDiv.style.display="block";setElementDescriptionForCalCell(descriptionElement,elementToDescribe);}
function setElementDescriptionForCalCell(descriptionElement,descriptionValue)
{var textDisplay=descriptionValue;if((!isblank(textDisplay))&&textDisplay.length>40)
{textDisplay="<table class='form' width='300'><tr><td>"+textDisplay+"</td></tr></table>";descriptionElement.align='left';}
else
{textDisplay="<table class='form'><tr><td>"+textDisplay+"</td></tr></table>";descriptionElement.align='center';}
descriptionElement.innerHTML=textDisplay;}
function hideDescForCalCell()
{showVerticalScrollBar();gObejectForCalCell=null;var descDiv=document.getElementById("descdiv");if(descDiv!=null)
{descDiv.style.display="none";descdiv_code.innerText="";descdiv_desc.innerText="";}}
function checkScrollIntoView(pObj)
{if(pObj.mousedown=="true")
{pObj.mousedown=null;}
else
pObj.scrollIntoView();}
function onSelectAllMouseDown(pObj)
{pObj.mousedown="true"}
function processFieldValueDrag()
{gDragField=event.srcElement;}
function processFieldValueDrop()
{if(gDragField)
{setTimeout("setDragFieldValue('"+gDragField.id+"')",200);gDragField=null;}}
function setDragFieldValue(fid)
{var vfield=document.getElementById(fid);if(vfield)
setFieldValue(fid,vfield.value);}
function processGridCellLinkClick(pLinkCell)
{if(!isObject(pLinkCell))
pLinkCell=event.srcElement;if(isObject(window.customGridCellLinkClick))
window.customGridCellLinkClick(pLinkCell)}
function getGridLinkCellTagName(pObj)
{if(isObject(pObj))
return pObj.dataFld;return null;}
function getGridLinkCellValue(pObj)
{if(isObject(pObj))
return pObj.innerText;}
function getRStatusFromMap(pMapname,pUStatus)
{var islandFIELDVALUES=document.getElementById("FIELDVALUES");var rstatusNode=islandFIELDVALUES.selectSingleNode("//ROW/"+pMapname+"_options/option[value='"+pUStatus+"']/display");if(rstatusNode!=null)
return rstatusNode.text;else
return null;}
function getTopOpenerWindow(pWindow)
{if(isObject(pWindow.dialogArguments))
{var vParentWindow=pWindow.dialogArguments.openerwindow;return getTopOpenerWindow(vParentWindow);}
else
return pWindow;}
