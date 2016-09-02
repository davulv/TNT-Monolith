--
-- TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
-- Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
--
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU General Public License as published by
-- the Free Software Foundation, either version 3 of the License.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
-- GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--


-- ----------------------------------------------------------------------------
-- Bill
-- ----------------------------------------------------------------------------
ALTER TABLE `Bill` ADD COLUMN `providerId` int;
ALTER TABLE `Bill` ADD index `ndx_bill_providerId` (`providerId`);
ALTER TABLE `Bill` ADD constraint `fk_bill_providerId` foreign key (`providerId`) references `Organization` (`id`);


-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------

-- Update version number
update Version set version='0.3';
