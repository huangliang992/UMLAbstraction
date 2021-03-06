/**
 ** MODULE NAME: 
 ** DataType.js
 **
 ** DESCRIPTION:
 **   Defines how are the elements of class diagrams of UML 2.
 **
 ** DEVELOPED BY:
 **
 ** SUPERVISED BY:
 **		José Raúl Romero, PhD (Associate Professor, University of Córdoba, Spain)
 **
 ** HISTORY:
 **
 ** CONTACT INFO:
 ** 	José Raúl Romero, http://www.jrromero.net
 **
 ** NOTES:
 **
 ** LICENSE & DISCLAIMER:
 **    Copyright (C) 2011 The authors
 **
 **    This program is free software: you can redistribute it and/or modify
 **    it under the terms of the GNU General Public License as published by
 **    the Free Software Foundation, either version 3 of the License, or
 **    (at your option) any later version.
 **
 **    This program is distributed in the hope that it will be useful,
 **    but WITHOUT ANY WARRANTY; without even the implied warranty of
 **    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 **    GNU General Public License for more details.
 **
 **    You should have received a copy of the GNU General Public License
 **    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **
**/

/**
 * DataType class constructor, creates a AcceptEventAction in the activity diagram
 *
 * @author Rafael Molina Linares
 * @update 19/8/2011
 *
 * @class DataType
 * @extends Rectangular
 *
 */

var DataType = function( params ) {

  params = params || {};
  DataType.baseConstructor.call(this,params);
}
JSFun.extend(DataType,Rectangular);

/**
 * Set the name of the element UML
 *
 * @author Rafael Molina Linares
 * @update 13/10/2011
 *
 * @method setName
 * @param {String} text Text to establish the new name
 *
 */

DataType.prototype.setName = function( text ){
	this._components[2].setValue( text );
}


/**
 * Adds new item to the stereotype fields component of the element UML
 *
 * @author Rafael Molina Linares
 * @update 13/10/2011
 *
 * @method addStereotype
 * @param {String} text Text that will contain the new field of the stereotype component
 *
 */

DataType.prototype.addStereotype = function(text){
	var text = text || '';
	this._components[1].addField( '\xAB' + text + '\xBB' );
}


/**
 * Adds new item to the attribute fields component of the element UML
 *
 * @author Rafael Molina Linares
 * @update 16/11/2011
 *
 * @method addAttribute
 * @param {String} text Text that will contain the new field of the component
 *
 */

DataType.prototype.addAttribute = function(text){
	var text = text || '';
	this._components[3].addField( text );
}




/**
 * Return the stereotypes of the element UML in array's form
 *
 * @author Rafael Molina Linares
 * @update 18/11/2011
 *
 * @method getStereotypes
 * @return {Array} Array with the stereotypes components of the element
 *
 */

DataType.prototype.getStereotypes = function( ){
	return	this._components[1]._childs;
}


/**
 * Returns the name of the element UML
 *
 * @author Rafael Molina Linares
 * @update 18/11/2011
 *
 * @method getName
 * @return {String} Text of the element's name
 *
 */
DataType.prototype.getName = function( ){
	return this._components[2].getValue();
}


/**
 * Return the component that contains the attributes of the element UML in array's form
 *
 * @author Rafael Molina Linares
 * @update 18/11/2011
 *
 * @method getAttributes
 * @return {Array} Array with the attribute components of the element
 *
 */

DataType.prototype.getAttributes = function( ){
	return	this._components[3]._childs;
}




/**
 * Returns the name field component of the element UML
 *
 * @author Alejandro Arrabal Hidalgo
 * @update 22/09/2012
 *
 * @method getNameAsComponent
 * @return {Component} Stereotype field component of the element UML
 *
 */
DataType.prototype.getNameAsComponent = function( ){
	return this._components[2];
}




/**
 * Returns the stereotype fields component of the element UML
 *
 * @author Alejandro Arrabal Hidalgo
 * @update 22/09/2012
 *
 * @method addOperation
 * @param {String} text Text that will contain the new field of the component
 *
 */

DataType.prototype.addOperation = function(text){
	var text = text || '';
	this._components[4].addField( text );
}




/**
 * Return the component that contains the operations of the element UML in array's form
 *
 * @author Alejandro Arrabal Hidalgo
 * @update 22/09/2012
 *
 * @method getOperations
 * @return {Array} Array with the operation components of the element
 *
 */

DataType.prototype.getOperations = function( ){
	return	this._components[4]._childs;
}






/**
 * Returns the stereotype fields component of the element UML
 *
 * @author Rafael Molina Linares
 * @update 13/10/2011
 *
 * @method getStereotype
 * @return {Component} Stereotype fields components of the element UML
 *
 */

DataType.prototype.getStereotype = function(){		
	return this._components[1];
}